package com.ngtnkhoa.springecommerce.exception;

import com.ngtnkhoa.springecommerce.dto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<BaseResponse> handleValidationException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(error -> {
      String field = error.getField();
      String message = error.getDefaultMessage();
      errors.put(field, message);
    });

    BaseResponse body = BaseResponse.builder()
        .status(false)
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .message("Validation failed")
        .data(errors)
        .build();

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(body);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<BaseResponse> handleIllegalArgument(IllegalArgumentException ex) {
    return ResponseEntity
        .badRequest()
        .body(BaseResponse.builder()
            .status(false)
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .message(ex.getMessage())
            .build()
        );
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public BaseResponse handleGeneral(Exception ex) {
    return BaseResponse.builder()
        .status(false)
        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message("Internal error: " + ex.getMessage())
        .build();
  }
}
