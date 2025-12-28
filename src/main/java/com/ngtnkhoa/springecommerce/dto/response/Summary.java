package com.ngtnkhoa.springecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Summary {
  private Set<String> brands;
  private Double minPrice;
  private Double maxPrice;
}
