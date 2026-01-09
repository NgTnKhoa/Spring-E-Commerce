package com.ngtnkhoa.springecommerce.entity;

import com.ngtnkhoa.springecommerce.enums.CategoryStatus;
import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@EqualsAndHashCode(callSuper = true, exclude = {"parent", "children"})
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

  @Column(name = "image")
  private String image;

  @Column(name = "featured")
  private boolean featured;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private CategoryStatus status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private Category parent;

  @OneToMany(mappedBy = "parent", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
  private List<Category> children;

  @OneToMany(mappedBy = "category")
  private List<Product> products;
}
