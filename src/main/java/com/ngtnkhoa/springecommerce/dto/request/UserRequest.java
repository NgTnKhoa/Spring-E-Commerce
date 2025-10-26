package com.ngtnkhoa.springecommerce.dto.request;

import com.ngtnkhoa.springecommerce.validator.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Email is required")
  @Email(message = "Invalid email format")
  private String email;

  @NotBlank(message = "Username is required")
  @Size(min = 5, max = 15, message = "Username must be between 5 and 15 characters long")
  private String username;

  @ValidPassword
  private String password;

  @Pattern(regexp = "^[0-9]{10}$", message = "User mobile phone must have 10 digits")
  private String phoneNumber;
}
