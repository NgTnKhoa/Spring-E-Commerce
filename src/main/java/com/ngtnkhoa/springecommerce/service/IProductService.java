package com.ngtnkhoa.springecommerce.service;

import com.ngtnkhoa.springecommerce.dto.request.ProductRequest;
import com.ngtnkhoa.springecommerce.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface IProductService {

  @Transactional(readOnly = true)
  Page<ProductResponse> findAll(int page, int size);

  @Transactional
  ProductResponse create(ProductRequest productRequest);

  @Transactional
  ProductResponse update(Long id, ProductRequest productRequest);

  @Transactional
  void delete(Long id);

  ProductResponse findById(Long id);

  ProductResponse findBySlug(String slug);

  Page<ProductResponse> findByCategoryId(Long categoryId, int page, int size);

  Page<ProductResponse> findByParams(Boolean featured, int page, int size);

  Set<String> findColorsByCategoryId(Long categoryId);
}
