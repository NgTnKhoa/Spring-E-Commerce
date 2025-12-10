package com.ngtnkhoa.springecommerce.mapper;

import com.ngtnkhoa.springecommerce.dto.CategoryDTO;
import com.ngtnkhoa.springecommerce.entity.Category;
import com.ngtnkhoa.springecommerce.dto.request.CategoryRequest;
import com.ngtnkhoa.springecommerce.dto.response.CategoryResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import com.ngtnkhoa.springecommerce.util.SlugUtil;

@Mapper(componentModel = "spring", imports = SlugUtil.class)
public interface CategoryMapper {

  @Mapping(target = "parentId", expression = "java(category.getParent() != null ? category.getParent().getId() : null)")
  @Mapping(target = "parentName", expression = "java(category.getParent() != null ? category.getParent().getName() : null)")
  CategoryDTO toCategoryDTO(Category category);

  CategoryResponse toCategoryResponse(CategoryDTO categoryDTO);

  @Mapping(target = "slug", expression = "java(SlugUtil.toSlug(categoryRequest.getName()))")
  @Mapping(target = "parent", ignore = true)
  @Mapping(target = "children", ignore = true)
  Category toCategoryEntity(CategoryRequest categoryRequest);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "parent", ignore = true)
  @Mapping(target = "children", ignore = true)
  Category toCategoryEntity(CategoryRequest categoryRequest, @MappingTarget Category category);


}
