package com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info;

import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.RestaurantMainInfoResInterface;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantMainInfoListResDto {

    private List<RestaurantMainInfoResInterface> restaurantList;

    public RestaurantMainInfoListResDto(List<RestaurantMainInfoResInterface> restaurantList) {
        this.restaurantList = restaurantList;
    }
}