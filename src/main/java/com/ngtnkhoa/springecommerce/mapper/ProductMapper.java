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

@Mapper(componentModel = "spring", imports = SlugUtil.class, uses = {CategoryMapper.class})
public interface ProductMapper {

  @Mapping(source = "categories", target = "categories")
  @Mapping(source = "colors", target = "colors")
  ProductDTO toProductDTO(Product product);

  @Mapping(source = "categories", target = "categories")
  ProductResponse toProductResponse(ProductDTO productDTO);

  @Mapping(source = "colors", target = "colors")
  @Mapping(target = "slug", expression = "java(SlugUtil.toSlug(productRequest.getName()))")
  Product toProductEntity(ProductRequest productRequest);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "categories", ignore = true)
  Product toProductEntity(ProductRequest productRequest, @MappingTarget Product product);
}
