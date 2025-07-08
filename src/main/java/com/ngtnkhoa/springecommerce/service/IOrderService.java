package com.ngtnkhoa.springecommerce.service;

import com.ngtnkhoa.springecommerce.dto.request.OrderRequest;
import com.ngtnkhoa.springecommerce.dto.response.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IOrderService {

  @Transactional(readOnly = true)
  List<OrderResponse> findAll();

  @Transactional
  OrderResponse create(OrderRequest orderRequest);

  @Transactional
  OrderResponse update(Long id, OrderRequest orderRequest);

  @Transactional
  void delete(Long id);

  OrderResponse findById(Long id);

  Page<OrderResponse> findByUserId(Long userId, int page, int size);
}
