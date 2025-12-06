package com.ngtnkhoa.springecommerce.service;

import com.ngtnkhoa.springecommerce.dto.request.UserRequest;
import com.ngtnkhoa.springecommerce.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserService {

  @Transactional(readOnly = true)
  Page<UserResponse> findAll(int page, int size);

  @Transactional
  UserResponse update(Long id, UserRequest userRequest);

  @Transactional
  void delete(Long id);

  UserResponse findById(Long id);

  UserResponse findByUsername(String username);
}
