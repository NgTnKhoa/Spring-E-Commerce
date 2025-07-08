package com.ngtnkhoa.springecommerce.service.impl;

import com.ngtnkhoa.springecommerce.entity.Order;
import com.ngtnkhoa.springecommerce.entity.OrderItem;
import com.ngtnkhoa.springecommerce.mapper.OrderItemMapper;
import com.ngtnkhoa.springecommerce.mapper.OrderMapper;
import com.ngtnkhoa.springecommerce.dto.request.OrderRequest;
import com.ngtnkhoa.springecommerce.dto.response.OrderResponse;
import com.ngtnkhoa.springecommerce.repository.OrderItemRepository;
import com.ngtnkhoa.springecommerce.repository.OrderRepository;
import com.ngtnkhoa.springecommerce.service.IOrderService;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

  private final OrderMapper orderMapper;
  private final OrderItemMapper orderItemMapper;
  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;

  @Override
  public List<OrderResponse> findAll() {
    return orderRepository.findAll()
        .stream().map(order -> orderMapper
            .toOrderResponse(orderMapper
                .toOrderDTO(order))).toList();
  }

  @Override
  public OrderResponse create(OrderRequest orderRequest) {
    Order order = orderRepository.save(orderMapper.toOrderEntity(orderRequest));
    List<OrderItem> orderItems = orderRequest.getOrderItems().stream()
        .map(itemReq -> {
          OrderItem item = orderItemMapper.toOrderItemEntity(itemReq);
          item.setOrder(order);
          return item;
        }).toList();

    order.setOrderItems(orderItems);
    orderItemRepository.saveAll(orderItems);

    return orderMapper
        .toOrderResponse(orderMapper
            .toOrderDTO(orderRepository
                .save(order)));
  }

  @Override
  public OrderResponse update(Long id, OrderRequest orderRequest) {
    Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
    return orderMapper.toOrderResponse(orderMapper
        .toOrderDTO(orderRepository
            .save(orderMapper
                .toOrderEntity(orderRequest, order))));
  }

  @Override
  public void delete(Long id) {
    if (orderRepository.existsById(id)) {
      orderRepository.deleteById(id);
    } else {
      throw new IllegalArgumentException("Order not found");
    }
  }

  @Override
  public OrderResponse findById(Long id) {
    return orderMapper
        .toOrderResponse(orderMapper
            .toOrderDTO(orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"))));
  }

  @Override
  public Page<OrderResponse> findByUserId(Long userId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
    Page<Order> orders = orderRepository.findAllByUser_Id(userId, pageable);
    return orders
        .map(order -> orderMapper
            .toOrderResponse(orderMapper
                .toOrderDTO(order)));
  }
}
