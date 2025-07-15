package com.ngtnkhoa.springecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ngtnkhoa.springecommerce.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  boolean existsByName(String name);

  boolean existsBySlug(String slug);

  Category findBySlug(String slug);

  @Query("""
          SELECT c FROM Category c
          WHERE (:featured IS NULL OR c.featured = :featured)
      """)
  Page<Category> filter(
      @Param("featured") Boolean featured,
      Pageable pageable
  );
}
