package com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info;

import com.contest.chaeso.domain.restaurant.review.img.api.dto.res.RestaurantReviewImgListDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantInfoResDto {

    private RestaurantInfoDto restaurantInfoDto;
    private List<RestaurantImgListDto> restaurantImgListDto;
    private List<RestaurantReviewImgListDto> restaurantReviewImgListDto;

    public RestaurantInfoResDto(RestaurantInfoDto restaurantInfoDto, List<RestaurantImgListDto> restaurantImgListDtoList, List<RestaurantReviewImgListDto> restaurantReviewImgListDtoList) {
        this.restaurantInfoDto = restaurantInfoDto;
        this.restaurantImgListDto = restaurantImgListDtoList;
        this.restaurantReviewImgListDto = restaurantReviewImgListDtoList;
    }
}
