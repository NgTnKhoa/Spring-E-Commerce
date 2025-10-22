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
@Table(name = "products")
public class Product extends Base {

  @Column(name = "name")
  private String name;

  @Column(name = "slug")
  private String slug;

  @Column(name = "description")
  private String description;

  @ElementCollection
  @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
  @Column(name = "images")
  private List<String> images;

  @ElementCollection
  @CollectionTable(name = "product_colors", joinColumns = @JoinColumn(name = "product_id"))
  @Column(name = "colors")
  private List<String> colors;

  @Column(name = "price")
  private double price;

  @Column(name = "discount")
  private double discount;

  @Column(name = "brand")
  private String brand;

  @Column(name = "status")
  private String status;

  @Column(name = "featured")
  private boolean featured;

  @OneToMany(mappedBy = "product")
  @Cascade(CascadeType.ALL)
  private List<Review> reviews;

  @OneToMany(mappedBy = "product")
  @Cascade(CascadeType.ALL)
  private List<OrderItem> orderItems;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
}
