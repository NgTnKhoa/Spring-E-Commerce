package com.ngtnkhoa.springecommerce.controller;

import com.ngtnkhoa.springecommerce.dto.request.LoginRequest;
import com.ngtnkhoa.springecommerce.dto.request.RegisterRequest;
import com.ngtnkhoa.springecommerce.dto.response.AuthenticationResponse;
import com.ngtnkhoa.springecommerce.dto.response.BaseResponse;
import com.ngtnkhoa.springecommerce.service.IAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final IAuthenticationService IAuthenticationService;

  @PostMapping("/register")
  public ResponseEntity<BaseResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
    AuthenticationResponse authenticationResponse = IAuthenticationService.register(registerRequest);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Register Successfully")
            .status(true)
            .data(authenticationResponse)
            .statusCode(200)
            .build());
  }

  @PostMapping("/login")
  public ResponseEntity<BaseResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
    AuthenticationResponse authenticationResponse = IAuthenticationService.login(loginRequest);
    return ResponseEntity
        .ok()
        .body(BaseResponse.builder()
            .message("Login successfully")
            .status(true)
            .data(authenticationResponse)
            .statusCode(200)
            .build());
  }

  @PostMapping("/refresh-token")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    IAuthenticationService.refreshToken(request, response);
  }

  @PostMapping("/validate-token")
  public ResponseEntity<Boolean> isTokenValid(@RequestParam String token) {
    return ResponseEntity.ok(IAuthenticationService.isTokenValid(token));
  }
}
