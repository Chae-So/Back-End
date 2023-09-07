package com.contest.chaeso.domain.restaurant.review.img.api.dto.res;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantReviewImgListDto {

    private String rtReviewImgLink;

    public RestaurantReviewImgListDto(String rtReviewImgLink) {
        this.rtReviewImgLink = rtReviewImgLink;
    }
}
