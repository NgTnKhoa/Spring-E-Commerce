package com.ngtnkhoa.springecommerce.dto.response;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class ProductListResponse {
  private Page<ProductResponse> products;
  private Double minPrice;
  private Double maxPrice;
  
  public ProductListResponse(Page<ProductResponse> products, Double minPrice, Double maxPrice) {
    this.products = products;
    this.minPrice = minPrice;
    this.maxPrice = maxPrice;
  }
}