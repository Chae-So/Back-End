package com.contest.chaeso.test;


import com.contest.chaeso.domain.restaurant.review.review.domain.RestaurantReview;
import com.contest.chaeso.domain.restaurant.review.review.domain.repository.RestaurantReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TestService {

    private final RestaurantReviewRepository restaurantReviewRepository;


    public List<RestaurantReview>  findRestaurantReviewByImgByRestaurant(){
        List<RestaurantReview> restaurantReviewByImgByRestaurant = restaurantReviewRepository.findRestaurantReviewByImgByRestaurant();
        return restaurantReviewByImgByRestaurant;
    }

}
