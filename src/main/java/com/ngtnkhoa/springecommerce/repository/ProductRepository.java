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
          SELECT p FROM Product p
          WHERE (:featured IS NULL OR p.featured = :featured)
      """)
  Page<Product> filter(
      @Param("featured") Boolean featured,
      Pageable pageable
  );
}