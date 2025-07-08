package com.ngtnkhoa.springecommerce.dto.request;

import com.ngtnkhoa.springecommerce.validator.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

  @NotBlank(message = "Username is required")
  @Size(min = 5, max = 15, message = "Username must be between 5 and 15 characters long")
  private String username;

  @NotBlank(message = "Password is required")
  @ValidPassword
  private String password;

}
