package com.ngtnkhoa.springecommerce.mapper;

import com.ngtnkhoa.springecommerce.dto.ProductDTO;
import com.ngtnkhoa.springecommerce.entity.Product;
import com.ngtnkhoa.springecommerce.dto.request.ProductRequest;
import com.ngtnkhoa.springecommerce.dto.response.ProductResponse;
import com.ngtnkhoa.springecommerce.util.SlugUtil;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", imports = SlugUtil.class)
public interface ProductMapper {

  @Mapping(target = "categoryId", source = "category.id")
  @Mapping(target = "categoryName", source = "category.name")
  @Mapping(source = "colors", target = "colors")
  ProductDTO toProductDTO(Product product);

  ProductResponse toProductResponse(ProductDTO productDTO);

  @Mapping(source = "colors", target = "colors")
  @Mapping(target = "slug", expression = "java(SlugUtil.toSlug(productRequest.getName()))")
  Product toProductEntity(ProductRequest productRequest);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "category.id", source = "categoryId")
  Product toProductEntity(ProductRequest productRequest, @MappingTarget Product product);
}
