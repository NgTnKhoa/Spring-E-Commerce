package com.ngtnkhoa.springecommerce.mapper;

import com.ngtnkhoa.springecommerce.dto.UserDTO;
import com.ngtnkhoa.springecommerce.entity.User;
import com.ngtnkhoa.springecommerce.dto.request.UserRequest;
import com.ngtnkhoa.springecommerce.dto.response.UserResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDTO toUserDTO(User user);

  UserResponse toUserResponse(UserDTO userDTO);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void toUserEntity(UserRequest userUpdateRequest, @MappingTarget User user);
}
