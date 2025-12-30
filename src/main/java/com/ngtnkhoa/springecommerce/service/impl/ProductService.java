package com.ngtnkhoa.springecommerce.service.impl;

import com.ngtnkhoa.springecommerce.dto.request.ProductRequest;
import com.ngtnkhoa.springecommerce.dto.response.ProductListResponse;
import com.ngtnkhoa.springecommerce.dto.response.ProductResponse;
import com.ngtnkhoa.springecommerce.dto.response.Summary;
import com.ngtnkhoa.springecommerce.entity.Category;
import com.ngtnkhoa.springecommerce.entity.Product;
import com.ngtnkhoa.springecommerce.mapper.ProductMapper;
import com.ngtnkhoa.springecommerce.repository.CategoryRepository;
import com.ngtnkhoa.springecommerce.repository.ProductRepository;
import com.ngtnkhoa.springecommerce.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

  private final ProductMapper productMapper;
  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  @Override
  public ProductListResponse findAll(
          Boolean featured,
          String categorySlug,
          List<String> brands,
          Double minPrice,
          Double maxPrice,
          String keyword,
          int page,
          int size
  ) {
    Long categoryId = null;

    if (categorySlug != null && !categorySlug.isBlank()) {
      categoryId = categoryRepository.findBySlug(categorySlug)
              .orElseThrow(() -> new RuntimeException("Category not found")).getId();
    }

    Pageable pageable = PageRequest.of(
            page,
            size,
            Sort.by("name").ascending()
    );

    Page<Product> products = productRepository.filter(
            featured,
            categoryId,
            brands,
            minPrice,
            maxPrice,
            keyword,
            pageable
    );

    Page<ProductResponse> productResponses = products
            .map(product -> productMapper
                    .toProductResponse(productMapper
                            .toProductDTO(product)));

    Set<String> summaryBrands = productRepository.findBrandsByFilter(categoryId);
    Double summaryMinPrice = productRepository.findMinPriceByFilter(featured, categoryId, brands, minPrice, maxPrice, keyword);
    Double summaryMaxPrice = productRepository.findMaxPriceByFilter(featured, categoryId, brands, minPrice, maxPrice, keyword);
    Summary summary = new Summary(summaryBrands, summaryMinPrice, summaryMaxPrice);

    return new ProductListResponse(productResponses, summary);
  }

  @Override
  public ProductResponse create(ProductRequest productRequest) {
    if (productRepository.existsByName(productRequest.getName())) {
      throw new IllegalArgumentException("Product name already exists");
    }

    Product product = productMapper.toProductEntity(productRequest);

    Category category = categoryRepository.findById(productRequest.getCategoryId())
            .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + productRequest.getCategoryId()));
    product.setCategory(category);

    return productMapper
            .toProductResponse(productMapper
                    .toProductDTO(productRepository
                            .save(product)));
  }

  @Override
  public ProductResponse update(Long id, ProductRequest productRequest) {
    Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    productMapper.toProductEntity(productRequest, product);

    Category category = categoryRepository.findById(productRequest.getCategoryId())
            .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + productRequest.getCategoryId()));
    product.setCategory(category);

    return productMapper
            .toProductResponse(productMapper
                    .toProductDTO(productRepository
                            .save(product)));
  }

  @Override
  public void delete(Long id) {
    if (!productRepository.existsById(id)) {
      throw new IllegalArgumentException("Product not found");
    }
    productRepository.deleteById(id);
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
    return productMapper
            .toProductResponse(productMapper
                    .toProductDTO(productRepository.findBySlug(slug)
                            .orElseThrow(() -> new IllegalArgumentException("Product not found"))));
  }

  @Override
  public Set<String> findColorsByCategoryId(Long categoryId) {
    return productRepository.findColorsByCategoryId(categoryId);
  }
}
