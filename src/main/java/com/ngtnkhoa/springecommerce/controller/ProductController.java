package com.ngtnkhoa.springecommerce.controller;

import com.ngtnkhoa.springecommerce.dto.response.ProductResponse;
import com.ngtnkhoa.springecommerce.dto.response.ReviewResponse;
import com.ngtnkhoa.springecommerce.service.IProductService;

import java.util.List;

import com.ngtnkhoa.springecommerce.service.IReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ngtnkhoa.springecommerce.dto.request.ProductRequest;
import com.ngtnkhoa.springecommerce.dto.response.BaseResponse;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final IProductService productService;
  private final IReviewService reviewService;

  @GetMapping
  public ResponseEntity<BaseResponse> findAll(
      @RequestParam(required = false) Boolean featured,
      @RequestParam(required = false) String categorySlug,
      @RequestParam(required = false) List<String> brands,
      @RequestParam(required = false) Double minPrice,
      @RequestParam(required = false) Double maxPrice,
      @RequestParam(required = false) String keyword,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size
  ) {
    Page<ProductResponse> products = productService.findAll(
        featured,
        categorySlug,
        brands,
        minPrice,
        maxPrice,
        keyword,
        page,
        size
    );
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Get products successfully")
            .status(true)
            .data(products)
            .statusCode(HttpStatus.OK.value())
            .build());
  }

  @PostMapping
  public ResponseEntity<BaseResponse> create(@Valid @RequestBody ProductRequest productRequest) {
    ProductResponse createdProduct = productService.create(productRequest);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Create product successfully")
            .data(createdProduct)
            .statusCode(HttpStatus.CREATED.value())
            .status(true)
            .build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<BaseResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest productRequest) {
    ProductResponse updatedProduct = productService.update(id, productRequest);

    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Update product successfully")
            .data(updatedProduct)
            .statusCode(HttpStatus.OK.value())
            .status(true)
            .build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
    productService.delete(id);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Delete product successfully")
            .statusCode(HttpStatus.NO_CONTENT.value())
            .status(true)
            .build());
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaseResponse> findById(@PathVariable Long id) {
    ProductResponse product = productService.findById(id);
    return ResponseEntity.ok()
        .body(BaseResponse.builder()
            .message("Get product successfully")
            .status(true)
            .statusCode(HttpStatus.OK.value())
            .data(product)
            .build());
  }

  @GetMapping("/slug/{slug}")
  public ResponseEntity<BaseResponse> findBySlug(@PathVariable String slug) {
    ProductResponse product = productService.findBySlug(slug);
    return ResponseEntity.ok()
        .body(BaseResponse.builder()
            .message("Get product successfully")
            .status(true)
            .statusCode(HttpStatus.OK.value())
            .data(product)
            .build());
  }

  @GetMapping("/{id}/reviews")
  public ResponseEntity<BaseResponse> findReviewsById(@PathVariable Long id) {
    List<ReviewResponse> reviews = reviewService.findByProductId(id);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Get all reviews by product ID successfully")
            .status(true)
            .data(reviews)
            .statusCode(HttpStatus.OK.value())
            .build());
  }
}
