package com.ngtnkhoa.springecommerce.mapper;

import com.ngtnkhoa.springecommerce.dto.OrderDTO;
import com.ngtnkhoa.springecommerce.entity.Order;
import com.ngtnkhoa.springecommerce.dto.request.OrderRequest;
import com.ngtnkhoa.springecommerce.dto.response.OrderResponse;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    @Mapping(source = "orderItems", target = "orderItems")
    OrderDTO toOrderDTO(Order order);

    OrderResponse toOrderResponse(OrderDTO orderDTO);

    @Mapping(source = "orderItems", target = "orderItems")
    Order toOrderEntity(OrderRequest orderRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order toOrderEntity(OrderRequest orderRequest, @MappingTarget Order order);
}
