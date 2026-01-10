package com.ngtnkhoa.springecommerce.dto;

import com.ngtnkhoa.springecommerce.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

  private Long id;

  private String name;

  private String email;

  private String username;

  private String phoneNumber;

  private UserRole role;
}
