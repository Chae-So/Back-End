package com.contest.chaeso.domain.restaurant.review.review.api.dto.res;

import com.contest.chaeso.domain.restaurant.review.img.domain.RestaurantReviewImg;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantReviewImgResDto {

    private String rtReviewImgLink;

    public RestaurantReviewImgResDto(RestaurantReviewImg restaurantReviewImg) {
        this.rtReviewImgLink = restaurantReviewImg.getRtReviewImgLink();
    }
}
