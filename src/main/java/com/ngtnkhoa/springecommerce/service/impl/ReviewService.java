package com.ngtnkhoa.springecommerce.service.impl;

import com.ngtnkhoa.springecommerce.entity.Review;
import com.ngtnkhoa.springecommerce.dto.request.ReviewRequest;
import com.ngtnkhoa.springecommerce.dto.response.ReviewResponse;
import com.ngtnkhoa.springecommerce.repository.ReviewRepository;
import com.ngtnkhoa.springecommerce.service.IReviewService;
import com.ngtnkhoa.springecommerce.mapper.ReviewMapper;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {

  private final ReviewMapper reviewMapper;
  private final ReviewRepository reviewRepository;

  @Override
  public ReviewResponse create(ReviewRequest reviewRequest) {
    return reviewMapper
        .toReviewResponse(reviewMapper
            .toReviewDTO(reviewRepository
                .save(reviewMapper
                    .toReviewEntity(reviewRequest))));
  }

  @Override
  public ReviewResponse update(Long id, ReviewRequest reviewRequest) {
    Review review = reviewRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Review not found"));
    return reviewMapper
        .toReviewResponse(reviewMapper
            .toReviewDTO(reviewRepository
                .save(reviewMapper
                    .toReviewEntity(reviewRequest, review))));
  }

  @Override
  public void delete(Long id) {
    if (reviewRepository.existsById(id)) {
      reviewRepository.deleteById(id);
    } else {
      throw new IllegalArgumentException("Review not found");
    }
  }

  @Override
  public ReviewResponse findById(Long id) {
    return reviewMapper
        .toReviewResponse(reviewMapper
            .toReviewDTO(reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"))));
  }

  @Override
  public List<ReviewResponse> findByProductId(Long productId) {
    return reviewRepository.findAllByProduct_Id(productId)
        .stream()
        .map(review -> reviewMapper
            .toReviewResponse(reviewMapper
                .toReviewDTO(review))).toList();
  }

  @Override
  public Page<ReviewResponse> findByUserId(Long userId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
    Page<Review> reviews = reviewRepository.findAllByUser_Id(userId, pageable);
    return reviews
        .map(review -> reviewMapper
            .toReviewResponse(reviewMapper
                .toReviewDTO(review)));
  }
}
