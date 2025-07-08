package com.ngtnkhoa.springecommerce.service;

import com.ngtnkhoa.springecommerce.dto.request.LoginRequest;
import com.ngtnkhoa.springecommerce.dto.request.RegisterRequest;
import com.ngtnkhoa.springecommerce.dto.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IAuthenticationService {

  AuthenticationResponse login(LoginRequest loginRequest);

  AuthenticationResponse register(RegisterRequest registerRequest);

  void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

  boolean isTokenValid(String token);
}
