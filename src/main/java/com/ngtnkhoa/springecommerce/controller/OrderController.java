package com.ngtnkhoa.springecommerce.controller;

import com.ngtnkhoa.springecommerce.dto.response.BaseResponse;
import com.ngtnkhoa.springecommerce.dto.response.OrderResponse;
import com.ngtnkhoa.springecommerce.service.IOrderService;
import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ngtnkhoa.springecommerce.dto.request.OrderRequest;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final IOrderService orderService;

  @GetMapping
  public ResponseEntity<BaseResponse> findAll() {
    List<OrderResponse> orders = orderService.findAll();
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Get all orders successfully")
            .status(true)
            .data(orders)
            .statusCode(HttpStatus.OK.value())
            .build());
  }

  @PostMapping
  public ResponseEntity<BaseResponse> create(@Valid @RequestBody OrderRequest orderRequest) {
    OrderResponse createdOrder = orderService.create(orderRequest);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Create order successfully")
            .data(createdOrder)
            .statusCode(HttpStatus.CREATED.value())
            .status(true)
            .build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<BaseResponse> update(@PathVariable Long id, @Valid @RequestBody OrderRequest orderRequest) {
    OrderResponse updatedOrder = orderService.update(id, orderRequest);

    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Update order successfully")
            .data(updatedOrder)
            .statusCode(HttpStatus.OK.value())
            .status(true)
            .build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
    orderService.delete(id);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Delete order successfully")
            .statusCode(HttpStatus.NO_CONTENT.value())
            .status(true)
            .build());
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaseResponse> findById(@PathVariable Long id) {
    OrderResponse order = orderService.findById(id);
    return ResponseEntity.ok()
        .body(BaseResponse.builder()
            .message("Get order successfully")
            .status(true)
            .statusCode(HttpStatus.OK.value())
            .data(order)
            .build());
  }
}
