package com.ngtnkhoa.springecommerce.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngtnkhoa.springecommerce.config.security.JwtService;
import com.ngtnkhoa.springecommerce.enums.TokenType;
import com.ngtnkhoa.springecommerce.enums.UserRole;
import com.ngtnkhoa.springecommerce.entity.Token;
import com.ngtnkhoa.springecommerce.entity.User;
import com.ngtnkhoa.springecommerce.dto.request.LoginRequest;
import com.ngtnkhoa.springecommerce.dto.request.RegisterRequest;
import com.ngtnkhoa.springecommerce.dto.response.AuthenticationResponse;
import com.ngtnkhoa.springecommerce.mapper.UserMapper;
import com.ngtnkhoa.springecommerce.repository.TokenRepository;
import com.ngtnkhoa.springecommerce.repository.UserRepository;
import com.ngtnkhoa.springecommerce.service.IAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final UserMapper userMapper;

  @Override
  public AuthenticationResponse register(RegisterRequest registerRequest) {
    if (userRepository.existsByEmail(registerRequest.getEmail())) {
      throw new IllegalArgumentException("Email already exists");
    }

    if (userRepository.existsByUsername(registerRequest.getUsername())) {
      throw new IllegalArgumentException("Username already exists");
    }

    if (userRepository.existsByPhoneNumber(registerRequest.getPhoneNumber())) {
      throw new IllegalArgumentException("Phone number already exists");
    }

    User user = User.builder()
            .name(registerRequest.getName())
            .email(registerRequest.getEmail())
            .username(registerRequest.getUsername())
            .password(passwordEncoder.encode(registerRequest.getPassword()))
            .phoneNumber(registerRequest.getPhoneNumber())
            .role(UserRole.USER)
            .build();

    User savedUser = userRepository.save(user);
    String jwtToken = jwtService.generateToken(user);
    String refreshToken = jwtService.generateRefreshToken(user);

    saveUserToken(savedUser, jwtToken);

    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .id(savedUser.getId())
            .user(userMapper.toUserResponse(
                    userMapper.toUserDTO(savedUser)))
            .build();
  }

  @Override
  public AuthenticationResponse login(LoginRequest loginRequest) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            )
    );

    User user = userRepository.findByUsername(loginRequest.getUsername())
            .orElseThrow(() -> new IllegalArgumentException("Wrong username or password"));
    String jwtToken = jwtService.generateToken(user);
    String refreshToken = jwtService.generateRefreshToken(user);

    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);

    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .id(user.getId())
            .user(userMapper.toUserResponse(
                    userMapper.toUserDTO(user)
            ))
            .build();
  }

  private void saveUserToken(User savedUser, String jwtToken) {
    Token token = Token.builder()
            .user(savedUser)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();

    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    List<Token> validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());

    if (validUserTokens.isEmpty()) {
      return;
    }

    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });

    tokenRepository.saveAll(validUserTokens);
  }

  @Override
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String username;

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return;
    }

    refreshToken = authHeader.substring(7);
    username = jwtService.extractUsername(refreshToken);

    if (username != null) {
      User user = userRepository.findByUsername(username)
              .orElseThrow();

      if (jwtService.validateToken(refreshToken, user)) {
        String accessToken = jwtService.generateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        new ObjectMapper().writeValue(response.getOutputStream(), authenticationResponse);
      }
    }
  }

  @Override
  public boolean isTokenValid(String token) {
    return tokenRepository.existsByToken(token);
  }
}
