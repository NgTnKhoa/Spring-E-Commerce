package com.ngtnkhoa.springecommerce.controller;

import com.ngtnkhoa.springecommerce.dto.request.PaymentRequest;
import com.ngtnkhoa.springecommerce.dto.response.BaseResponse;
import com.ngtnkhoa.springecommerce.dto.response.PaymentResponse;
import com.ngtnkhoa.springecommerce.service.IPaymentService;
import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
