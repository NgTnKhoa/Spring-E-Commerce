package com.ngtnkhoa.springecommerce.service.impl;

import com.ngtnkhoa.springecommerce.entity.Payment;
import com.ngtnkhoa.springecommerce.mapper.PaymentMapper;
import com.ngtnkhoa.springecommerce.dto.request.PaymentRequest;
import com.ngtnkhoa.springecommerce.dto.response.PaymentResponse;
import com.ngtnkhoa.springecommerce.repository.OrderRepository;
import com.ngtnkhoa.springecommerce.repository.PaymentRepository;
import com.ngtnkhoa.springecommerce.service.IPaymentService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

  private final PaymentMapper paymentMapper;
  private final PaymentRepository paymentRepository;
  private final OrderRepository orderRepository;

  @Override
  public List<PaymentResponse> findAll() {
    return paymentRepository.findAll()
        .stream()
        .map(payment -> paymentMapper
            .toPaymentResponse(paymentMapper
                .toPaymentDTO(payment))).toList();
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
    if (paymentRepository.existsById(id)) {
      paymentRepository.deleteById(id);
    } else {
      throw new IllegalArgumentException("Payment not found");
    }
  }

  @Override
  public PaymentResponse findById(Long id) {
    return paymentMapper
        .toPaymentResponse(paymentMapper
            .toPaymentDTO(paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"))));
  }

  @Override
  public Page<PaymentResponse> findByUserId(Long userId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
    Page<Payment> payments = paymentRepository.findAllByUser_Id(userId, pageable);
    return payments
        .map(payment -> paymentMapper
            .toPaymentResponse(paymentMapper
                .toPaymentDTO(payment)));
  }
}
