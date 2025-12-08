package com.ngtnkhoa.springecommerce.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ngtnkhoa.springecommerce.dto.request.CreatePaymentLinkRequestBody;
import com.ngtnkhoa.springecommerce.dto.request.PaymentRequest;
import com.ngtnkhoa.springecommerce.dto.response.PaymentResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface IPaymentService {

  @Transactional(readOnly = true)
  Page<PaymentResponse> findAll(
          String status,
          String method,
          int page,
          int size
  );

  @Transactional
  PaymentResponse create(PaymentRequest paymentRequest);

  @Transactional
  PaymentResponse update(Long id, PaymentRequest paymentRequest);

  @Transactional
  void delete(Long id);

  PaymentResponse findById(Long id);

  PaymentResponse findByTransactionCode(String transactionCode);

  Page<PaymentResponse> findByUserId(Long userId, int page, int size);

  //  order

  ObjectNode createPaymentLink(CreatePaymentLinkRequestBody RequestBody);

  ObjectNode getOrderById(Long orderId);

  ObjectNode cancelOrder(Long orderId);

  ObjectNode confirmWebhook(Map<String, String> requestBody);

  //  payment

  ObjectNode payosTransferHandler(ObjectNode body) throws JsonProcessingException;

  //  checkout

  void checkout(HttpServletRequest request, HttpServletResponse httpServletResponse);
}
