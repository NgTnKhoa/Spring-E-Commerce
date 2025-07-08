package com.ngtnkhoa.springecommerce.controller;

import com.ngtnkhoa.springecommerce.dto.request.ReviewRequest;
import com.ngtnkhoa.springecommerce.dto.response.BaseResponse;
import com.ngtnkhoa.springecommerce.dto.response.ProductResponse;
import com.ngtnkhoa.springecommerce.dto.response.ReviewResponse;
import com.ngtnkhoa.springecommerce.service.IReviewService;

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

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

  private final IReviewService reviewService;

  @PostMapping
  public ResponseEntity<BaseResponse> create(@Valid @RequestBody ReviewRequest reviewRequest) {
    ReviewResponse createdReview = reviewService.create(reviewRequest);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Create review successfully")
            .data(createdReview)
            .statusCode(HttpStatus.CREATED.value())
            .status(true)
            .build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<BaseResponse> update(@PathVariable Long id, @Valid @RequestBody ReviewRequest reviewRequest) {
    ReviewResponse updatedReview = reviewService.update(id, reviewRequest);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Update review successfully")
            .data(updatedReview)
            .statusCode(HttpStatus.OK.value())
            .status(true)
            .build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
    reviewService.delete(id);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Delete review successfully")
            .data(null)
            .statusCode(HttpStatus.NO_CONTENT.value())
            .status(true)
            .build());
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaseResponse> findById(@PathVariable Long id) {
    ReviewResponse review = reviewService.findById(id);
    return ResponseEntity.ok()
        .body(BaseResponse.builder()
            .message("Get review successfully")
            .status(true)
            .statusCode(HttpStatus.OK.value())
            .data(review)
            .build());
  }
}
