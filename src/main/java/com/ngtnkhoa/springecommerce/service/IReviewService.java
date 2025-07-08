package com.ngtnkhoa.springecommerce.service;

import com.ngtnkhoa.springecommerce.dto.request.ReviewRequest;
import com.ngtnkhoa.springecommerce.dto.response.ReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IReviewService {

  @Transactional
  ReviewResponse create(ReviewRequest reviewRequest);

  @Transactional
  ReviewResponse update(Long id, ReviewRequest reviewRequest);

  @Transactional
  void delete(Long id);

  ReviewResponse findById(Long id);

  List<ReviewResponse> findByProductId(Long categoryId);

  Page<ReviewResponse> findByUserId(Long userId, int page, int size);
}
