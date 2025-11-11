package com.ngtnkhoa.springecommerce.entity.emb;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductColor {

  @Column(name = "name")
  @NotBlank(message = "Color name must not be blank")
  private String path;

  @Column(name = "hex")
  @NotBlank(message = "Hex code must be greater than 0")
  private String hex;
}
