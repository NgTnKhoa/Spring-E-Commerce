package com.ngtnkhoa.springecommerce.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ngtnkhoa.springecommerce.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  @JsonProperty("accessToken")
  private String accessToken;

  @JsonProperty("refreshToken")
  private String refreshToken;

  private Long id;

  private String username;

  private UserRole role;
}
