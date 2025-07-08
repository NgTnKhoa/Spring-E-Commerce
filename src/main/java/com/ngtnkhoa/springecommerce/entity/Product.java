package com.ngtnkhoa.springecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "products")
public class Product extends Base {

  @Column(name = "name")
  private String name;

  @Column(name = "slug")
  private String slug;

  @Column(name = "description")
  private String description;

  @Column(name = "images")
  private List<String> images;

  @Column(name = "price")
  private double price;

  @Column(name = "discount")
  private double discount;

  @Column(name = "status")
  private String status;

  @Column(name = "colors")
  private List<String> colors;

  @Column(name = "featured")
  private boolean featured;

  @OneToMany(mappedBy = "product")
  @Cascade(CascadeType.ALL)
  private List<Review> reviews;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
}
