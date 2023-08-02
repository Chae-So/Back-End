package com.contest.chaeso.domain.restaurant.review.review.api.dto.res;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantReviewListResDto {

    private List<RestaurantReviewResDto> restaurantReviewList;
    private List<RestaurantScoreCountInterface> restaurantScoreCount;

    public RestaurantReviewListResDto(List<RestaurantReviewResDto> restaurantReviewResDto, List<RestaurantScoreCountInterface> restaurantScoreCountInterface) {
        this.restaurantReviewList = restaurantReviewResDto;
        this.restaurantScoreCount = restaurantScoreCountInterface;
    }
}
