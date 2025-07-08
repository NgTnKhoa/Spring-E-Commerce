package com.ngtnkhoa.springecommerce.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Map;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || value.trim().isEmpty()) {
      return buildViolation(context, "Password is required");
    }

    Map<String, String> rules = Map.of(
        ".*[A-Z].*", "Password must contain at least one uppercase letter",
        ".*[a-z].*", "Password must contain at least one lowercase letter",
        ".*\\d.*", "Password must contain at least one digit",
        ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*", "Password must contain at least one special character"
    );

    if (value.length() < 5) {
      return buildViolation(context, "Password must be at least 5 characters");
    }

    for (Map.Entry<String, String> entry : rules.entrySet()) {
      if (!value.matches(entry.getKey())) {
        return buildViolation(context, entry.getValue());
      }
    }

    return true;
  }

  private boolean buildViolation(ConstraintValidatorContext context, String message) {
    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate(message)
        .addConstraintViolation();
    return false;
  }
}
