package com.ngtnkhoa.springecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngtnkhoa.springecommerce.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  boolean existsByName(String name);
}
