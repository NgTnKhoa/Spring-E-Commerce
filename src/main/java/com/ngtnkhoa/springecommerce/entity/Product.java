package com.ngtnkhoa.springecommerce.entity;

import com.ngtnkhoa.springecommerce.entity.emb.ProductColor;
import com.ngtnkhoa.springecommerce.entity.emb.ProductImage;
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

  @Column(name = "price")
  private double price;

  @Column(name = "stock")
  private int stock;

  @Column(name = "rating")
  private int rating;

  @Column(name = "discount")
  private double discount;

  @Column(name = "brand")
  private String brand;

  @Column(name = "status")
  private String status;

  @Column(name = "featured", columnDefinition = "boolean default false")
  private boolean featured;

  @Column(name = "main_image")
  private String mainImage;

  @ElementCollection
  @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
  @Cascade(CascadeType.ALL)
  private List<ProductImage> images;

  @ElementCollection
  @CollectionTable(name = "product_colors", joinColumns = @JoinColumn(name = "product_id"))
  @Cascade(CascadeType.ALL)
  private List<ProductColor> colors;

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
