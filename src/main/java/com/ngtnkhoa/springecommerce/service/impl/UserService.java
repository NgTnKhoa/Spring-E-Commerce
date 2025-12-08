package com.ngtnkhoa.springecommerce.service.impl;

import com.ngtnkhoa.springecommerce.mapper.UserMapper;
import com.ngtnkhoa.springecommerce.dto.request.UserRequest;
import com.ngtnkhoa.springecommerce.dto.response.UserResponse;
import com.ngtnkhoa.springecommerce.service.IUserService;
import com.ngtnkhoa.springecommerce.entity.User;
import com.ngtnkhoa.springecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;

  @Override
  public Page<UserResponse> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
    Page<User> users = userRepository.findAll(pageable);
    return users
            .map(user -> userMapper
                    .toUserResponse(userMapper
                            .toUserDTO(user)));
  }

  @Override
  public UserResponse update(Long id, UserRequest userRequest) {
    User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    userMapper.toUserEntity(userRequest, user);
    return userMapper
            .toUserResponse(userMapper
                    .toUserDTO(userRepository
                            .save(user)));
  }

  @Override
  public void delete(Long id) {
    if (userRepository.existsById(id)) {
      userRepository.deleteById(id);
    } else {
      throw new IllegalArgumentException("User not found");
    }
  }

  @Override
  public UserResponse findById(Long id) {
    return userMapper
            .toUserResponse(userMapper
                    .toUserDTO(userRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("User not found"))));
  }

  @Override
  public UserResponse findByUsername(String username) {
    return userMapper
            .toUserResponse(userMapper
                    .toUserDTO(userRepository.findByUsername(username)
                            .orElseThrow(() -> new IllegalArgumentException("User not found"))));
  }
}
