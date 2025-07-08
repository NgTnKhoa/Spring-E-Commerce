package com.ngtnkhoa.springecommerce.repository;

import com.ngtnkhoa.springecommerce.entity.Order;
import com.ngtnkhoa.springecommerce.dto.response.OrderResponse;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

  Page<Order> findAllByUser_Id(Long userId, Pageable pageable);
}