package com.ngtnkhoa.springecommerce.service.impl;

import com.ngtnkhoa.springecommerce.dto.request.CategoryRequest;
import com.ngtnkhoa.springecommerce.dto.response.CategoryResponse;
import com.ngtnkhoa.springecommerce.entity.Category;
import com.ngtnkhoa.springecommerce.mapper.CategoryMapper;
import com.ngtnkhoa.springecommerce.repository.CategoryRepository;
import com.ngtnkhoa.springecommerce.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

  private final CategoryMapper categoryMapper;
  private final CategoryRepository categoryRepository;

  @Override
  public Page<CategoryResponse> findAll(Boolean featured, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
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
    }

    Category category = categoryMapper.toCategoryEntity(categoryRequest);

    if (categoryRequest.getParentId() != null) {
      Category parent = categoryRepository.findById(categoryRequest.getParentId())
              .orElseThrow(() -> new IllegalArgumentException("Parent category not found"));
      category.setParent(parent);
    }

    Category savedCategory = categoryRepository.save(category);
    return categoryMapper.toCategoryResponse(categoryMapper.toCategoryDTO(savedCategory));
  }

  @Override
  public CategoryResponse update(Long id, CategoryRequest categoryRequest) {
    Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Category not found"));

    categoryMapper.toCategoryEntity(categoryRequest, category);

    if (categoryRequest.getParentId() != null) {
      if (categoryRequest.getParentId().equals(id)) {
        throw new IllegalArgumentException("Category cannot be its own parent");
      }
      Category parent = categoryRepository.findById(categoryRequest.getParentId())
              .orElseThrow(() -> new IllegalArgumentException("Parent category not found"));
      category.setParent(parent);
    } else {
      category.setParent(null);
    }

    Category savedCategory = categoryRepository.save(category);
    return categoryMapper.toCategoryResponse(categoryMapper.toCategoryDTO(savedCategory));
  }

  @Override
  public void delete(Long id) {
    if (!categoryRepository.existsById(id)) {
      throw new IllegalArgumentException("Category not found");
    }
    categoryRepository.deleteById(id);
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
    return categoryMapper
            .toCategoryResponse(categoryMapper
                    .toCategoryDTO(categoryRepository.findBySlug(slug)
                            .orElseThrow(() -> new IllegalArgumentException("Category not found"))));
  }

  @Override
  public List<CategoryResponse> findCategoryTrees() {
    return categoryRepository
            .findRootCategories()
            .stream()
            .map(this::buildCategoryTree)
            .toList();
  }

  @Override
  public List<CategoryResponse> findCategoryBreadcrumb(Long id) {
    Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Category not found"));

    LinkedList<CategoryResponse> breadcrumb = new LinkedList<>();
    Category current = category;

    while (current != null) {
      CategoryResponse categoryResponse = categoryMapper.toCategoryResponse(
              categoryMapper.toCategoryDTO(current));
      breadcrumb.addFirst(categoryResponse);
      current = current.getParent();
    }

    return breadcrumb;
  }

  private CategoryResponse buildCategoryTree(Category category) {
    CategoryResponse categoryResponse = categoryMapper.toCategoryResponse(
            categoryMapper.toCategoryDTO(category));

    List<CategoryResponse> children = category.getChildren().stream()
            .map(this::buildCategoryTree)
            .toList();

    categoryResponse.setChildren(children);
    return categoryResponse;
  }
}
