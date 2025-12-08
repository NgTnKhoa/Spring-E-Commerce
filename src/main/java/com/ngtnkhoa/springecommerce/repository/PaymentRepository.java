package com.ngtnkhoa.springecommerce.repository;

import com.ngtnkhoa.springecommerce.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

  Page<Payment> findAllByUser_Id(Long userId, Pageable pageable);

  Optional<Payment> findByTransactionCode(String transactionCode);

  @Query("""
              SELECT DISTINCT p FROM Payment p
              WHERE (:status IS NULL OR p.status = :status)
                AND (:method IS NULL OR p.method = :method)
          """)
  Page<Payment> filter(
          @Param("status") String orderCode,
          @Param("method") String method,
          Pageable pageable
  );
}