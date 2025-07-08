package com.ngtnkhoa.springecommerce.config.application;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "storage")
@Getter
@Setter
public class StorageConfig {

  private String directory;
  private String maxSize;
}

