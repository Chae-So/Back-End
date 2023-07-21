package com.contest.chaeso.domain.restaurant.review.review.domain.repository;

import com.contest.chaeso.domain.restaurant.review.review.domain.RestaurantReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantReviewRepository extends JpaRepository<RestaurantReview, Long> {
}
