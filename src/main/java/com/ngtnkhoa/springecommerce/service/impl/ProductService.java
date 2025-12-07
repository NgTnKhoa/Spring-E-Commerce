package com.ngtnkhoa.springecommerce.service.impl;

import com.ngtnkhoa.springecommerce.dto.ProductDTO;
import com.ngtnkhoa.springecommerce.entity.Category;
import com.ngtnkhoa.springecommerce.entity.Product;
import com.ngtnkhoa.springecommerce.mapper.ProductMapper;
import com.ngtnkhoa.springecommerce.dto.request.ProductRequest;
import com.ngtnkhoa.springecommerce.dto.response.ProductResponse;
import com.ngtnkhoa.springecommerce.repository.CategoryRepository;
import com.ngtnkhoa.springecommerce.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ngtnkhoa.springecommerce.service.IProductService;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

  private final ProductMapper productMapper;
  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  @Override
  public Page<ProductResponse> findAll(
          Boolean featured,
          Long categoryId,
          List<String> colors,
          List<String> brands,
          Double minPrice,
          Double maxPrice,
          String keyword,
          int page,
          int size
  ) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
    Page<Product> products = productRepository.filter(
            featured,
            categoryId,
            colors,
            brands,
            minPrice,
            maxPrice,
            keyword,
            pageable
    );
    return products
            .map(product -> productMapper
                    .toProductResponse(productMapper
                            .toProductDTO(product)));
  }

  @Override
  public ProductResponse create(ProductRequest productRequest) {
    if (productRepository.existsByName(productRequest.getName())) {
      throw new IllegalArgumentException("Product name already exists");
    }

    Product product = productMapper.toProductEntity(productRequest);
    
    List<Category> categories = productRequest.getCategoryIds().stream()
            .map(categoryId -> categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + categoryId)))
            .collect(Collectors.toCollection(ArrayList::new));
    product.setCategories(categories);

    return productMapper
            .toProductResponse(productMapper
                    .toProductDTO(productRepository
                            .save(product)));
  }

  @Override
  public ProductResponse update(Long id, ProductRequest productRequest) {
    Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    productMapper.toProductEntity(productRequest, product);
    
    List<Category> categories = productRequest.getCategoryIds().stream()
            .map(categoryId -> categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + categoryId)))
            .collect(Collectors.toCollection(ArrayList::new));
    product.setCategories(categories);
    
    return productMapper
            .toProductResponse(productMapper
                    .toProductDTO(productRepository
                            .save(product)));
  }

  @Override
  public void delete(Long id) {
    if (productRepository.existsById(id)) {
      productRepository.deleteById(id);
    } else {
      throw new IllegalArgumentException("Product not found");
    }
  }

  @Override
  public ProductResponse findById(Long id) {
    return productMapper
            .toProductResponse(productMapper
                    .toProductDTO(productRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Product not found"))));
  }

  @Override
  public ProductResponse findBySlug(String slug) {
    if (!productRepository.existsBySlug(slug)) {
      throw new IllegalArgumentException("Product not found");
    }

    return productMapper
            .toProductResponse(productMapper
                    .toProductDTO(productRepository.findBySlug(slug)));
  }

  @Override
  public Set<String> findColorsByCategoryId(Long categoryId) {
    return productRepository.findColorsByCategoryId(categoryId);
  }
}
