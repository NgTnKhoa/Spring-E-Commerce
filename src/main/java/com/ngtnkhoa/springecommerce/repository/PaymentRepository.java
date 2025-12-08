package com.ngtnkhoa.springecommerce.repository;

import com.ngtnkhoa.springecommerce.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

  Page<Payment> findAllByUser_Id(Long userId, Pageable pageable);

  Optional<Payment> findByTransactionCode(String transactionCode);
}