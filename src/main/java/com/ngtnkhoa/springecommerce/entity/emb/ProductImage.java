package com.ngtnkhoa.springecommerce.entity.emb;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {

  @Column(name = "path")
  private String path;

  @Column(name = "sort_order")
  private int sortOrder;
}
