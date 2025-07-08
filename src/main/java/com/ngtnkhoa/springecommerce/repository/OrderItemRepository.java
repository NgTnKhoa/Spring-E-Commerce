package com.ngtnkhoa.springecommerce.repository;

import com.ngtnkhoa.springecommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}