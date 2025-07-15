package com.ngtnkhoa.springecommerce.service;

import com.ngtnkhoa.springecommerce.dto.request.ProductRequest;
import com.ngtnkhoa.springecommerce.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface IProductService {

  @Transactional(readOnly = true)
  Page<ProductResponse> findAll(
      Boolean featured,
      Long categoryId,
      List<String> colors,
      List<String> brands,
      Double minPrice,
      Double maxPrice,
      String keyword,
      int page,
      int size
  );

  @Transactional
  ProductResponse create(ProductRequest productRequest);

  @Transactional
  ProductResponse update(Long id, ProductRequest productRequest);

  @Transactional
  void delete(Long id);

  ProductResponse findById(Long id);

  ProductResponse findBySlug(String slug);

  Set<String> findColorsByCategoryId(Long categoryId);
}
