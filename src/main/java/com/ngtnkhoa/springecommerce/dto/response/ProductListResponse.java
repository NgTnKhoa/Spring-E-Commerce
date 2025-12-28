package com.ngtnkhoa.springecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class ProductListResponse {
  private Page<ProductResponse> products;
  private Summary summary;
}