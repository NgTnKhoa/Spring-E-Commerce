package com.ngtnkhoa.springecommerce.service.impl;

import com.ngtnkhoa.springecommerce.entity.Category;
import com.ngtnkhoa.springecommerce.mapper.CategoryMapper;
import com.ngtnkhoa.springecommerce.dto.request.CategoryRequest;
import com.ngtnkhoa.springecommerce.dto.response.CategoryResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ngtnkhoa.springecommerce.service.ICategoryService;
import com.ngtnkhoa.springecommerce.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

  private final CategoryMapper categoryMapper;
  private final CategoryRepository categoryRepository;

  @Override
  public Page<CategoryResponse> findAll(Boolean featured, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
    Page<Category> categories = categoryRepository.filter(featured, pageable);
    return categories
        .map(category -> categoryMapper
            .toCategoryResponse(categoryMapper
                .toCategoryDTO(category)));
  }

  @Override
  public CategoryResponse create(CategoryRequest categoryRequest) {
    if (categoryRepository.existsByName(categoryRequest.getName())) {
      throw new IllegalArgumentException("Category name already exists");
    } else {
      return categoryMapper
          .toCategoryResponse(categoryMapper
              .toCategoryDTO(categoryRepository
                  .save(categoryMapper
                      .toCategoryEntity(categoryRequest))));
    }
  }

  @Override
  public CategoryResponse update(Long id, CategoryRequest categoryRequest) {
    Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
    return categoryMapper
        .toCategoryResponse(categoryMapper
            .toCategoryDTO(categoryRepository
                .save(categoryMapper
                    .toCategoryEntity(categoryRequest, category))));
  }

  @Override
  public void delete(Long id) {
    if (categoryRepository.existsById(id)) {
      categoryRepository.deleteById(id);
    } else {
      throw new IllegalArgumentException("Category not found");
    }
  }

  @Override
  public CategoryResponse findById(Long id) {
    return categoryMapper
        .toCategoryResponse(categoryMapper
            .toCategoryDTO(categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"))));
  }

  @Override
  public CategoryResponse findBySlug(String slug) {
    if (categoryRepository.existsBySlug(slug)) {
      return categoryMapper
          .toCategoryResponse(categoryMapper
              .toCategoryDTO(categoryRepository.findBySlug(slug)));
    } else {
      throw new IllegalArgumentException("Category not found");
    }
  }
}
