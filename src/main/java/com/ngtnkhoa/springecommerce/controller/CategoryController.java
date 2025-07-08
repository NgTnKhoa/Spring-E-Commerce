package com.ngtnkhoa.springecommerce.controller;

import com.ngtnkhoa.springecommerce.dto.response.CategoryResponse;

import java.util.List;

import java.util.Set;

import com.ngtnkhoa.springecommerce.dto.response.ProductResponse;
import com.ngtnkhoa.springecommerce.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ngtnkhoa.springecommerce.service.ICategoryService;
import com.ngtnkhoa.springecommerce.dto.request.CategoryRequest;
import com.ngtnkhoa.springecommerce.dto.response.BaseResponse;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final ICategoryService categoryService;
  private final IProductService productService;

  @GetMapping
  public ResponseEntity<BaseResponse> findAll(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size
  ) {
    Page<CategoryResponse> categories = categoryService.findAll(page, size);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Get all categories successfully")
            .status(true)
            .data(categories)
            .statusCode(HttpStatus.OK.value())
            .build());
  }

  @PostMapping
  public ResponseEntity<BaseResponse> create(@Valid @RequestBody CategoryRequest categoryRequest) {
    CategoryResponse createdCategory = categoryService.create(categoryRequest);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Create category successfully")
            .data(createdCategory)
            .statusCode(HttpStatus.CREATED.value())
            .status(true)
            .build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<BaseResponse> update(@PathVariable Long id, @Valid @RequestBody CategoryRequest categoryRequest) {
    CategoryResponse updatedCategory = categoryService.update(id, categoryRequest);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Update category successfully")
            .data(updatedCategory)
            .statusCode(HttpStatus.OK.value())
            .status(true)
            .build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
    categoryService.delete(id);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Delete category successfully")
            .statusCode(HttpStatus.NO_CONTENT.value())
            .status(true)
            .build());
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaseResponse> findById(@PathVariable Long id) {
    CategoryResponse category = categoryService.findById(id);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Get category successfully")
            .status(true)
            .statusCode(HttpStatus.OK.value())
            .data(category)
            .build());
  }

  @GetMapping("/{id}/products")
  public ResponseEntity<BaseResponse> findProductsById(
      @PathVariable Long id,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size
  ) {
    Page<ProductResponse> products = productService.findByCategoryId(id, page, size);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Get all products by category ID successfully")
            .status(true)
            .data(products)
            .statusCode(HttpStatus.OK.value())
            .build());
  }

  @GetMapping("/{id}/colors")
  public ResponseEntity<BaseResponse> findColors(@PathVariable Long id) {
    Set<String> colors = productService.findColorsByCategoryId(id);
    return ResponseEntity.ok()
        .body(BaseResponse.builder()
            .message("Get all colors successfully")
            .status(true)
            .data(colors)
            .statusCode(HttpStatus.OK.value())
            .build());
  }
}
