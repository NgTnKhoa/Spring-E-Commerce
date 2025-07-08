package com.ngtnkhoa.springecommerce.entity;

import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends Base {

  @Column(name = "name")
  private String name;

  @Column(name = "slug")
  private String slug;

  @Column(name = "description")
  private String description;

  @Column(name = "images")
  private List<String> images;

  @Column(name = "featured")
  private boolean featured;

  @OneToMany(mappedBy = "category")
  @Cascade(CascadeType.ALL)
  private List<Product> products;
}
