package com.ngtnkhoa.springecommerce.service.impl;

import com.ngtnkhoa.springecommerce.dto.request.OrderRequest;
import com.ngtnkhoa.springecommerce.dto.response.OrderResponse;
import com.ngtnkhoa.springecommerce.entity.Order;
import com.ngtnkhoa.springecommerce.entity.OrderItem;
import com.ngtnkhoa.springecommerce.entity.Product;
import com.ngtnkhoa.springecommerce.mapper.OrderItemMapper;
import com.ngtnkhoa.springecommerce.mapper.OrderMapper;
import com.ngtnkhoa.springecommerce.repository.OrderItemRepository;
import com.ngtnkhoa.springecommerce.repository.OrderRepository;
import com.ngtnkhoa.springecommerce.repository.ProductRepository;
import com.ngtnkhoa.springecommerce.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

  private final OrderMapper orderMapper;
  private final OrderItemMapper orderItemMapper;
  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;
  private final ProductRepository productRepository;

  @Override
  public Page<OrderResponse> findAll(
          String orderCode,
          String status,
          String paymentMethod,
          int page,
          int size
  ) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").ascending());
    Page<Order> orders = orderRepository.filter(
            orderCode,
            status,
            paymentMethod,
            pageable
    );
    return orders
            .map(order -> orderMapper
                    .toOrderResponse(orderMapper
                            .toOrderDTO(order)));
  }

  @Override
  public OrderResponse create(OrderRequest orderRequest) {
    Order order = orderMapper.toOrderEntity(orderRequest);
    order.setOrderCode(generateOrderCode());
    orderRepository.save(order);

    List<OrderItem> orderItems = orderRequest.getOrderItems().stream()
            .map(itemReq -> {
              Product product = productRepository.findById(itemReq.getProductId())
                      .orElseThrow(() -> new IllegalArgumentException("Product not found"));
              OrderItem item = orderItemMapper.toOrderItemEntity(itemReq);
              item.setOrder(order);
              item.setProduct(product);
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
  public OrderResponse findByOrderCode(String orderCode) {
    return orderMapper
            .toOrderResponse(orderMapper
                    .toOrderDTO(orderRepository.findByOrderCode(orderCode)
                            .orElseThrow(() -> new IllegalArgumentException("Order not found"))));
  }

  @Override
  public Page<OrderResponse> findByUserId(Long userId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
    Page<Order> orders = orderRepository.findAllByUser_Id(userId, pageable);
    return orders
            .map(order -> orderMapper
                    .toOrderResponse(orderMapper
                            .toOrderDTO(order)));
  }

  private String generateOrderCode() {
    String prefix = "ORD";
    String year = String.valueOf(LocalDate.now().getYear());
    String uniquePart = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    return prefix + "-" + year + "-" + uniquePart;
  }
}
