package com.ngtnkhoa.springecommerce.controller;

import com.ngtnkhoa.springecommerce.dto.request.UserRequest;
import com.ngtnkhoa.springecommerce.dto.response.BaseResponse;
import com.ngtnkhoa.springecommerce.dto.response.OrderResponse;
import com.ngtnkhoa.springecommerce.dto.response.PaymentResponse;
import com.ngtnkhoa.springecommerce.dto.response.UserResponse;

import java.util.List;

import com.ngtnkhoa.springecommerce.service.IOrderService;
import com.ngtnkhoa.springecommerce.service.IPaymentService;
import com.ngtnkhoa.springecommerce.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final IUserService userService;
  private final IOrderService orderService;
  private final IPaymentService paymentService;

  @GetMapping
  public ResponseEntity<BaseResponse> findAll(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size
  ) {
    Page<UserResponse> users = userService.findAll(page, size);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Get all users successfully")
            .data(users)
            .statusCode(HttpStatus.OK.value())
            .status(true)
            .build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<BaseResponse> update(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {
    UserResponse userResponse = userService.update(id, userRequest);

    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Update user successfully")
            .data(userResponse)
            .statusCode(HttpStatus.OK.value())
            .status(true)
            .build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Delete user successfully")
            .statusCode(HttpStatus.NO_CONTENT.value())
            .status(true)
            .build());
  }

//  @GetMapping("/{id}")
//  public ResponseEntity<BaseResponse> findById(@PathVariable Long id) {
//    UserResponse user = userService.findById(id);
//    return ResponseEntity
//        .ok()
//        .body(BaseResponse.builder()
//            .message("Get user successfully")
//            .status(true)
//            .data(user)
//            .statusCode(HttpStatus.OK.value())
//            .build());
//  }

  @GetMapping("/{username}")
  public ResponseEntity<BaseResponse> findByUsername(@PathVariable String username) {
    UserResponse user = userService.findByUsername(username);
    return ResponseEntity
            .ok()
            .body(BaseResponse.builder()
                    .message("Get user successfully")
                    .status(true)
                    .data(user)
                    .statusCode(HttpStatus.OK.value())
                    .build());
  }

  @GetMapping("/{id}/orders")
  public ResponseEntity<BaseResponse> findOrdersById(
      @PathVariable Long id,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size
  ) {
    Page<OrderResponse> orders = orderService.findByUserId(id, page, size);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Get orders by user ID successfully")
            .status(true)
            .data(orders)
            .statusCode(HttpStatus.OK.value())
            .build());
  }

  @GetMapping("/{id}/payments")
  public ResponseEntity<BaseResponse> findPaymentsById(
      @PathVariable Long id,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size
  ) {
    Page<PaymentResponse> payments = paymentService.findByUserId(id, page, size);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Get orders by user ID successfully")
            .status(true)
            .data(payments)
            .statusCode(HttpStatus.OK.value())
            .build());
  }
}
