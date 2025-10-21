package com.ngtnkhoa.springecommerce.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ngtnkhoa.springecommerce.dto.request.CreatePaymentLinkRequestBody;
import com.ngtnkhoa.springecommerce.dto.request.PaymentRequest;
import com.ngtnkhoa.springecommerce.dto.response.BaseResponse;
import com.ngtnkhoa.springecommerce.dto.response.PaymentResponse;
import com.ngtnkhoa.springecommerce.service.IPaymentService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.payos.type.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

  private final IPaymentService paymentService;

  @GetMapping
  public ResponseEntity<BaseResponse> findAll() {
    List<PaymentResponse> payments = paymentService.findAll();
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Get all payments successfully")
            .status(true)
            .data(payments)
            .statusCode(HttpStatus.OK.value())
            .build());
  }

  @PostMapping
  public ResponseEntity<BaseResponse> create(@Valid @RequestBody PaymentRequest paymentRequest) {
    PaymentResponse createdPayment = paymentService.create(paymentRequest);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Create payment successfully")
            .data(createdPayment)
            .statusCode(HttpStatus.CREATED.value())
            .status(true)
            .build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<BaseResponse> update(@PathVariable Long id, @Valid @RequestBody PaymentRequest paymentRequest) {
    PaymentResponse updatedPayment = paymentService.update(id, paymentRequest);

    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Update payment successfully")
            .data(updatedPayment)
            .statusCode(HttpStatus.OK.value())
            .status(true)
            .build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
    paymentService.delete(id);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Delete payment successfully")
            .statusCode(HttpStatus.OK.value())
            .status(true)
            .build());
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaseResponse> findById(@PathVariable Long id) {
    PaymentResponse payment = paymentService.findById(id);
    return ResponseEntity.ok()
        .body(BaseResponse.builder()
            .message("Get payment successfully")
            .status(true)
            .statusCode(HttpStatus.OK.value())
            .data(payment)
            .build());
  }

  //  order

  @PostMapping(path = "/create")
  public ObjectNode createPaymentLink(@RequestBody CreatePaymentLinkRequestBody RequestBody) {
    return paymentService.createPaymentLink(RequestBody);
  }

  @GetMapping(path = "/{orderId}")
  public ObjectNode getOrderById(@PathVariable("orderId") long orderId) {
    return paymentService.getOrderById(orderId);
  }

  @PutMapping(path = "/{orderId}")
  public ObjectNode cancelOrder(@PathVariable("orderId") long orderId) {
    return paymentService.cancelOrder(orderId);
  }

  @PostMapping(path = "/confirm-webhook")
  public ObjectNode confirmWebhook(@RequestBody Map<String, String> requestBody) {
    return paymentService.confirmWebhook(requestBody);
  }

  //  payment

  @PostMapping(path = "/payos_transfer_handler")
  public ObjectNode payosTransferHandler(@RequestBody ObjectNode body)
      throws JsonProcessingException, IllegalArgumentException {
    return paymentService.payosTransferHandler(body);
  }

  //  checkout

  @RequestMapping(method = RequestMethod.POST, value = "/create-payment-link", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public void checkout(HttpServletRequest request, HttpServletResponse httpServletResponse) {
    paymentService.checkout(request, httpServletResponse);
  }
}
