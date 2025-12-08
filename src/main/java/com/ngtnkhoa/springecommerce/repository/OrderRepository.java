package com.ngtnkhoa.springecommerce.repository;

import com.ngtnkhoa.springecommerce.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

  Page<Order> findAllByUser_Id(Long userId, Pageable pageable);

  @Query("""
              SELECT DISTINCT o FROM Order o
              WHERE (:orderCode IS NULL OR o.orderCode = :orderCode)
                AND (:status IS NULL OR o.status = :status)
                AND (:paymentMethod IS NULL OR o.paymentMethod = :paymentMethod)
          """)
  Page<Order> filter(
          @Param("orderCode") String orderCode,
          @Param("categoryId") String status,
          @Param("colors") String paymentMethod,
          Pageable pageable
  );

  Optional<Order> findByOrderCode(String orderCode);
}