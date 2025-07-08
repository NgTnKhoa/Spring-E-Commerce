package com.ngtnkhoa.springecommerce.controller;

import com.ngtnkhoa.springecommerce.entity.ForgotPassword;
import com.ngtnkhoa.springecommerce.entity.User;
import com.ngtnkhoa.springecommerce.dto.request.ChangePassword;
import com.ngtnkhoa.springecommerce.repository.ForgotPasswordRepository;
import com.ngtnkhoa.springecommerce.repository.UserRepository;
import com.ngtnkhoa.springecommerce.service.impl.EmailService;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/forgot-password")
@RequiredArgsConstructor
public class ForgotPasswordController {

  private final UserRepository userRepository;
  private final EmailService emailService;
  private final ForgotPasswordRepository forgotPasswordRepository;
  private final PasswordEncoder passwordEncoder;

  @PostMapping("/verify/{email}")
  public ResponseEntity<String> verifyEmail(@PathVariable("email") String email) {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("Please provide a valid email");
    } else {
      int otp = otpGenerator();

      ForgotPassword forgotPassword = ForgotPassword.builder()
          .otp(otp)
          .expirationDate(new Date(System.currentTimeMillis() + 70 * 1000))
          .user(user)
          .build();

      emailService.sendEmail(email, "OTP for forgot password request", "This is the OTP for your forgot password request: " + otp);

      forgotPasswordRepository.save(forgotPassword);

      return ResponseEntity.ok("Email for forgot password request has been sent");
    }
  }

  @PostMapping("/verify-otp/{otp}/{email}")
  public ResponseEntity<String> verifyOtp(@PathVariable("otp") Integer otp, @PathVariable("email") String email) {
    User user = userRepository.findByEmail(email);

    if (user == null) {
      throw new UsernameNotFoundException("Please provide a valid email");
    } else {
      ForgotPassword forgotPassword = forgotPasswordRepository.findByOtpAndUser(otp, user).orElseThrow(() -> new IllegalArgumentException("OTP không hợp lệ cho email: " + email));

      if (forgotPassword.getExpirationDate().before(Date.from(Instant.now()))) {
        forgotPasswordRepository.deleteById(forgotPassword.getId());
        return new ResponseEntity<>("OTP has expired", HttpStatus.EXPECTATION_FAILED);
      }

      return ResponseEntity.ok("Valid OTP");
    }
  }

  @PostMapping("/change-password/{email}")
  public ResponseEntity<String> changePassword(@PathVariable("email") String email, @Valid @RequestBody ChangePassword changePassword) {
    if (!Objects.equals(changePassword.password(), changePassword.repeatPassword())) {
      return new ResponseEntity<>("Please re-enter password", HttpStatus.EXPECTATION_FAILED);
    }

    String encodedPassword = passwordEncoder.encode(changePassword.password());
    userRepository.updatePassword(email, encodedPassword);

    return ResponseEntity.ok("Password changed successfully");
  }

  private Integer otpGenerator() {
    Random random = new Random();
    return random.nextInt(100_000, 999_999);
  }
}
