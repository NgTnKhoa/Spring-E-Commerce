package com.ngtnkhoa.springecommerce.mapper;

import com.ngtnkhoa.springecommerce.dto.ReviewDTO;
import com.ngtnkhoa.springecommerce.entity.Review;
import com.ngtnkhoa.springecommerce.dto.request.ReviewRequest;
import com.ngtnkhoa.springecommerce.dto.response.ReviewResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "product.id", target = "productId")
    ReviewDTO toReviewDTO(Review review);

    ReviewResponse toReviewResponse(ReviewDTO reviewDTO);

    @Mapping(source = "productId", target = "product.id")
    Review toReviewEntity(ReviewRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Review toReviewEntity(ReviewRequest request, @MappingTarget Review review);
}
