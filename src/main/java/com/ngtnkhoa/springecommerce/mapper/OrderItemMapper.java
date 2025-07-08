package com.ngtnkhoa.springecommerce.mapper;

import com.ngtnkhoa.springecommerce.dto.OrderItemDTO;
import com.ngtnkhoa.springecommerce.entity.OrderItem;
import com.ngtnkhoa.springecommerce.dto.request.OrderItemRequest;
import com.ngtnkhoa.springecommerce.dto.response.OrderItemResponse;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

  @Mapping(source = "order.id", target = "orderId")
  OrderItemDTO toOrderItemDTO(OrderItem orderItem);

  OrderItemResponse toOrderItemResponse(OrderItemDTO orderItemDTO);

  OrderItem toOrderItemEntity(OrderItemRequest orderItemRequest);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  OrderItem toOrderItemEntity(OrderItemRequest orderItemRequest, @MappingTarget OrderItem orderItem);
}
