package com.ngtnkhoa.springecommerce.service;

import com.ngtnkhoa.springecommerce.dto.request.CategoryRequest;
import com.ngtnkhoa.springecommerce.dto.response.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICategoryService {

  @Transactional(readOnly = true)
  Page<CategoryResponse> findAll(Boolean featured, int page, int size);

  @Transactional
  CategoryResponse create(CategoryRequest categoryRequest);

  @Transactional
  CategoryResponse update(Long id, CategoryRequest categoryRequest);

  @Transactional
  void delete(Long id);

  CategoryResponse findById(Long id);

  CategoryResponse findBySlug(String slug);

  List<CategoryResponse> findCategoryTrees();

  List<CategoryResponse> findCategoryBreadcrumb(Long id);
}
