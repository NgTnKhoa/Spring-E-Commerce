package com.ngtnkhoa.springecommerce.mapper;

import com.ngtnkhoa.springecommerce.dto.PaymentDTO;
import com.ngtnkhoa.springecommerce.entity.Payment;
import com.ngtnkhoa.springecommerce.dto.request.PaymentRequest;
import com.ngtnkhoa.springecommerce.dto.response.PaymentResponse;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentDTO toPaymentDTO(Payment payment);

    PaymentResponse toPaymentResponse(PaymentDTO paymentDTO);

    Payment toPaymentEntity(PaymentRequest paymentRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Payment toPaymentEntity(PaymentRequest paymentRequest, @MappingTarget Payment payment);
}
