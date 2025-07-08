package com.ngtnkhoa.springecommerce.repository;

import java.util.List;

import com.ngtnkhoa.springecommerce.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ngtnkhoa.springecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findAllByCategory_Id(Long categoryId);

  boolean existsByName(String name);

  Page<Product> findAllByCategory_Id(Long categoryId, Pageable pageable);
}