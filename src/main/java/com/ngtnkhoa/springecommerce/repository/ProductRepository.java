package com.ngtnkhoa.springecommerce.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ngtnkhoa.springecommerce.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

  boolean existsByName(String name);

  Optional<Product> findBySlug(String slug);

  @Query("SELECT DISTINCT color FROM Product p JOIN p.colors color WHERE p.category.id = :categoryId")
  Set<String> findColorsByCategoryId(@Param("categoryId") Long categoryId);

  @Query("""
    SELECT DISTINCT p
    FROM Product p
    WHERE (:featured IS NULL OR p.featured = :featured)
      AND (:categoryId IS NULL OR p.category.id = :categoryId)
      AND (:brands IS NULL OR p.brand IN :brands)
      AND (:minPrice IS NULL OR p.price >= :minPrice)
      AND (:maxPrice IS NULL OR p.price <= :maxPrice)
      AND (:keyword IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')))
""")
  Page<Product> filter(
      @Param("featured") Boolean featured,
      @Param("categoryId") Long categoryId,
      @Param("brands") List<String> brands,
      @Param("minPrice") Double minPrice,
      @Param("maxPrice") Double maxPrice,
      @Param("keyword") String keyword,
      Pageable pageable
  );

  @Query("""
    SELECT DISTINCT p.brand
    FROM Product p
    WHERE (:featured IS NULL OR p.featured = :featured)
      AND (:categoryId IS NULL OR p.category.id = :categoryId)
      AND (:brands IS NULL OR p.brand IN :brands)
      AND (:minPrice IS NULL OR p.price >= :minPrice)
      AND (:maxPrice IS NULL OR p.price <= :maxPrice)
      AND (:keyword IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')))
""")
  Set<String> findBrandsByFilter(
          @Param("featured") Boolean featured,
          @Param("categoryId") Long categoryId,
          @Param("brands") List<String> brands,
          @Param("minPrice") Double minPrice,
          @Param("maxPrice") Double maxPrice,
          @Param("keyword") String keyword
  );

  @Query("""
    SELECT MIN(p.price)
    FROM Product p
    WHERE (:featured IS NULL OR p.featured = :featured)
      AND (:categoryId IS NULL OR p.category.id = :categoryId)
      AND (:brands IS NULL OR p.brand IN :brands)
      AND (:minPrice IS NULL OR p.price >= :minPrice)
      AND (:maxPrice IS NULL OR p.price <= :maxPrice)
      AND (:keyword IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')))
""")
  Double findMinPriceByFilter(
      @Param("featured") Boolean featured,
      @Param("categoryId") Long categoryId,
      @Param("brands") List<String> brands,
      @Param("minPrice") Double minPrice,
      @Param("maxPrice") Double maxPrice,
      @Param("keyword") String keyword
  );

  @Query("""
    SELECT MAX(p.price)
    FROM Product p
    WHERE (:featured IS NULL OR p.featured = :featured)
      AND (:categoryId IS NULL OR p.category.id = :categoryId)
      AND (:brands IS NULL OR p.brand IN :brands)
      AND (:minPrice IS NULL OR p.price >= :minPrice)
      AND (:maxPrice IS NULL OR p.price <= :maxPrice)
      AND (:keyword IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')))
""")
  Double findMaxPriceByFilter(
      @Param("featured") Boolean featured,
      @Param("categoryId") Long categoryId,
      @Param("brands") List<String> brands,
      @Param("minPrice") Double minPrice,
      @Param("maxPrice") Double maxPrice,
      @Param("keyword") String keyword
  );
}