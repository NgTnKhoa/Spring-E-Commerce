package com.ngtnkhoa.springecommerce.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ngtnkhoa.springecommerce.dto.request.CreatePaymentLinkRequestBody;
import com.ngtnkhoa.springecommerce.dto.request.PaymentRequest;
import com.ngtnkhoa.springecommerce.dto.response.PaymentResponse;
import com.ngtnkhoa.springecommerce.entity.Payment;
import com.ngtnkhoa.springecommerce.mapper.PaymentMapper;
import com.ngtnkhoa.springecommerce.repository.OrderRepository;
import com.ngtnkhoa.springecommerce.repository.PaymentRepository;
import com.ngtnkhoa.springecommerce.service.IPaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.payos.PayOS;
import vn.payos.type.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

  private final PaymentMapper paymentMapper;
  private final PaymentRepository paymentRepository;
  private final OrderRepository orderRepository;
  private final PayOS payOS;

  @Override
  public Page<PaymentResponse> findAll(
          String status,
          String method,
          int page,
          int size
  ) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
    Page<Payment> payments = paymentRepository.filter(
            status,
            method,
            pageable
    );
    return payments
            .map(payment -> paymentMapper
                    .toPaymentResponse(paymentMapper
                            .toPaymentDTO(payment)));
  }

  @Override
  public PaymentResponse create(PaymentRequest paymentRequest) {
    if (orderRepository.existsById(paymentRequest.getOrderId())) {
      return paymentMapper
              .toPaymentResponse(paymentMapper
                      .toPaymentDTO(paymentRepository
                              .save(paymentMapper
                                      .toPaymentEntity(paymentRequest))));
    } else {
      throw new IllegalArgumentException("Order not found");
    }
  }

  @Override
  public PaymentResponse update(Long id, PaymentRequest paymentRequest) {
    Payment payment = paymentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    return paymentMapper
            .toPaymentResponse(paymentMapper
                    .toPaymentDTO(paymentRepository
                            .save(paymentMapper
                                    .toPaymentEntity(paymentRequest, payment))));
  }

  @Override
  public void delete(Long id) {
    if (!paymentRepository.existsById(id)) {
      throw new IllegalArgumentException("Payment not found");
    }
    paymentRepository.deleteById(id);
  }

  @Override
  public PaymentResponse findById(Long id) {
    return paymentMapper
            .toPaymentResponse(paymentMapper
                    .toPaymentDTO(paymentRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Payment not found"))));
  }

  @Override
  public PaymentResponse findByTransactionCode(String transactionCode) {
    return paymentMapper
            .toPaymentResponse(paymentMapper
                    .toPaymentDTO(paymentRepository.findByTransactionCode(transactionCode)
                            .orElseThrow(() -> new IllegalArgumentException("Payment not found"))));
  }

  @Override
  public Page<PaymentResponse> findByUserId(Long userId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
    Page<Payment> payments = paymentRepository.findAllByUser_Id(userId, pageable);
    return payments
            .map(payment -> paymentMapper
                    .toPaymentResponse(paymentMapper
                            .toPaymentDTO(payment)));
  }

  //  order

  @Override
  public ObjectNode createPaymentLink(CreatePaymentLinkRequestBody RequestBody) {
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode response = objectMapper.createObjectNode();
    try {
      final String productName = RequestBody.getProductName();
      final String description = RequestBody.getDescription();
      final String returnUrl = RequestBody.getReturnUrl();
      final String cancelUrl = RequestBody.getCancelUrl();
      final int price = RequestBody.getPrice();
      // Gen order code
      String currentTimeString = String.valueOf(String.valueOf(new Date().getTime()));
      long orderCode = Long.parseLong(currentTimeString.substring(currentTimeString.length() - 6));

      ItemData item = ItemData.builder().name(productName).price(price).quantity(1).build();

      PaymentData paymentData = PaymentData.builder().orderCode(orderCode).description(description).amount(price)
              .item(item).returnUrl(returnUrl).cancelUrl(cancelUrl).build();

      CheckoutResponseData data = payOS.createPaymentLink(paymentData);

      response.put("error", 0);
      response.put("message", "success");
      response.set("data", objectMapper.valueToTree(data));
      return response;

    } catch (Exception e) {
      e.printStackTrace();
      response.put("error", -1);
      response.put("message", "fail");
      response.set("data", null);
      return response;

    }
  }

  @Override
  public ObjectNode getOrderById(Long orderId) {
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode response = objectMapper.createObjectNode();

    try {
      PaymentLinkData order = payOS.getPaymentLinkInformation(orderId);

      response.set("data", objectMapper.valueToTree(order));
      response.put("error", 0);
      response.put("message", "ok");
      return response;
    } catch (Exception e) {
      e.printStackTrace();
      response.put("error", -1);
      response.put("message", e.getMessage());
      response.set("data", null);
      return response;
    }
  }

  @Override
  public ObjectNode cancelOrder(Long orderId) {
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode response = objectMapper.createObjectNode();
    try {
      PaymentLinkData order = payOS.cancelPaymentLink(orderId, null);
      response.set("data", objectMapper.valueToTree(order));
      response.put("error", 0);
      response.put("message", "ok");
      return response;
    } catch (Exception e) {
      e.printStackTrace();
      response.put("error", -1);
      response.put("message", e.getMessage());
      response.set("data", null);
      return response;
    }
  }

  @Override
  public ObjectNode confirmWebhook(Map<String, String> requestBody) {
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode response = objectMapper.createObjectNode();
    try {
      String str = payOS.confirmWebhook(requestBody.get("webhookUrl"));
      response.set("data", objectMapper.valueToTree(str));
      response.put("error", 0);
      response.put("message", "ok");
      return response;
    } catch (Exception e) {
      e.printStackTrace();
      response.put("error", -1);
      response.put("message", e.getMessage());
      response.set("data", null);
      return response;
    }
  }

  //  payment

  @Override
  public ObjectNode payosTransferHandler(ObjectNode body) throws JsonProcessingException, IllegalArgumentException {
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode response = objectMapper.createObjectNode();
    Webhook webhookBody = objectMapper.treeToValue(body, Webhook.class);

    try {
      // Init Response
      response.put("error", 0);
      response.put("message", "Webhook delivered");
      response.set("data", null);

      WebhookData data = payOS.verifyPaymentWebhookData(webhookBody);
      System.out.println(data);
      return response;
    } catch (Exception e) {
      e.printStackTrace();
      response.put("error", -1);
      response.put("message", e.getMessage());
      response.set("data", null);
      return response;
    }
  }

  //  checkout

  @Override
  public void checkout(HttpServletRequest request, HttpServletResponse httpServletResponse) {
    try {
      final String baseUrl = getBaseUrl(request);
      final String productName = "Mì tôm hảo hảo ly";
      final String description = "Thanh toan don hang";
      final String returnUrl = baseUrl + "/success";
      final String cancelUrl = baseUrl + "/cancel";
      final int price = 2000;
      // Gen order code
      String currentTimeString = String.valueOf(new Date().getTime());
      long orderCode = Long.parseLong(currentTimeString.substring(currentTimeString.length() - 6));
      ItemData item = ItemData.builder().name(productName).quantity(1).price(price).build();
      PaymentData paymentData = PaymentData.builder().orderCode(orderCode).amount(price).description(description)
              .returnUrl(returnUrl).cancelUrl(cancelUrl).item(item).build();
      CheckoutResponseData data = payOS.createPaymentLink(paymentData);

      String checkoutUrl = data.getCheckoutUrl();

      httpServletResponse.setHeader("Location", checkoutUrl);
      httpServletResponse.setStatus(302);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getBaseUrl(HttpServletRequest request) {
    String scheme = request.getScheme();
    String serverName = request.getServerName();
    int serverPort = request.getServerPort();
    String contextPath = request.getContextPath();

    String url = scheme + "://" + serverName;
    if ((scheme.equals("http") && serverPort != 80) || (scheme.equals("https") && serverPort != 443)) {
      url += ":" + serverPort;
    }
    url += contextPath;
    return url;
  }
}
