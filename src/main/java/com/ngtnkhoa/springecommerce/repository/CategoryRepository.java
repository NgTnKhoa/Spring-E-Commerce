package com.ngtnkhoa.springecommerce.repository;

import com.ngtnkhoa.springecommerce.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  boolean existsByName(String name);

  Optional<Category> findBySlug(String slug);

  @Query("""
              SELECT c FROM Category c
              WHERE (:featured IS NULL OR c.featured = :featured)
              AND (:keyword IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%')))
          """)
  Page<Category> filter(
          @Param("featured") Boolean featured,
          @Param("keyword") String keyword,
          Pageable pageable
  );

  @Query("""
              SELECT c FROM Category c
              WHERE c.parent IS NULL
              ORDER BY c.name ASC
          """)
  List<Category> findRootCategories();

}
