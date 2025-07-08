package com.ngtnkhoa.springecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "file_datas")
public class FileData extends Base {

  @Column(name = "name")
  private String name;

  @Column(name = "type")
  private String type;

  @Column(name = "file_path")
  private String filePath;
}
