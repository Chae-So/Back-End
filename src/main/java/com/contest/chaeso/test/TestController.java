package com.contest.chaeso.test;


import com.contest.chaeso.domain.restaurant.review.review.domain.RestaurantReview;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    @GetMapping("")
    public ResponseEntity<Map<String, List<RestaurantReview>>> dd() {
        List<RestaurantReview> restaurantReviewByImgByRestaurant = testService.findRestaurantReviewByImgByRestaurant();

        Map<String, List<RestaurantReview>> test = new HashMap<>();
        test.put("test", restaurantReviewByImgByRestaurant);

        return new ResponseEntity<>(test, HttpStatus.OK);
    }
}
