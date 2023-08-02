package com.contest.chaeso.domain.restaurant.review.img.domain.repository;

import com.contest.chaeso.domain.restaurant.review.img.domain.RestaurantReviewImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RestaurantReviewImgRepository extends JpaRepository<RestaurantReviewImg, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from RestaurantReviewImg rri where rri.restaurantReview.rtReviewId = :rt_review_id")
    public int deleteByRestaurantReview(@Param("rt_review_id") Long rtReviewId);



}
