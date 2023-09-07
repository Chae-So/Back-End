package com.contest.chaeso.domain.restaurant.review.review.api.dto.res;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantReviewListResDto {

    private List<RestaurantReviewDto> restaurantReviewList;
    private List<RestaurantScoreCountInterface> restaurantScoreCount;

    public RestaurantReviewListResDto(List<RestaurantReviewDto> restaurantReviewResDto, List<RestaurantScoreCountInterface> restaurantScoreCountInterface) {
        this.restaurantReviewList = restaurantReviewResDto;
        this.restaurantScoreCount = restaurantScoreCountInterface;
    }
}
