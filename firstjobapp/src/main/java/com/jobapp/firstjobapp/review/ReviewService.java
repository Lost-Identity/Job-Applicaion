package com.jobapp.firstjobapp.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);

    Review getReviewById(Long companyId, Long reviewId);

    boolean createReview(Long companyId, Review review);

    boolean updateReview(Long companyId, Long reviewId, Review review);

    boolean deleteReview(Long companyId, Long reviewId);
}
