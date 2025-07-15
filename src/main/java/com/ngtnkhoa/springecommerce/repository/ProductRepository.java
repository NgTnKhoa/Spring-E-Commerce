package com.ngtnkhoa.springecommerce.repository;

import java.util.List;

import com.ngtnkhoa.springecommerce.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ngtnkhoa.springecommerce.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findAllByCategory_Id(Long categoryId);

  boolean existsByName(String name);

  Page<Product> findAllByCategory_Id(Long categoryId, Pageable pageable);

  Product findBySlug(String slug);

  boolean existsBySlug(String slug);

  @Query("""
    SELECT DISTINCT p
    FROM Product p
    LEFT JOIN p.colors c
    WHERE (:featured IS NULL OR p.featured = :featured)
      AND (:categoryId IS NULL OR p.category.id = :categoryId)
      AND (:colors IS NULL OR c IN :colors)
      AND (:colors IS NULL OR p.brand IN :brands)
      AND (:minPrice IS NULL OR p.price >= :minPrice)
      AND (:maxPrice IS NULL OR p.price <= :maxPrice)
      AND (:keyword IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')))
""")
  Page<Product> filter(
      @Param("featured") Boolean featured,
      @Param("categoryId") Long categoryId,
      @Param("colors") List<String> colors,
      @Param("brands") List<String> brands,
      @Param("minPrice") Double minPrice,
      @Param("maxPrice") Double maxPrice,
      @Param("keyword") String keyword,
      Pageable pageable
  );
}