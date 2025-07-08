package com.ngtnkhoa.springecommerce.service;

import com.ngtnkhoa.springecommerce.dto.request.PaymentRequest;
import com.ngtnkhoa.springecommerce.dto.response.PaymentResponse;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPaymentService {

  @Transactional(readOnly = true)
  List<PaymentResponse> findAll();

  @Transactional
  PaymentResponse create(PaymentRequest paymentRequest);

  @Transactional
  PaymentResponse update(Long id, PaymentRequest paymentRequest);

  @Transactional
  void delete(Long id);

  PaymentResponse findById(Long id);

  Page<PaymentResponse> findByUserId(Long userId, int page, int size);
}
