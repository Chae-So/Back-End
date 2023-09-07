package com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.menu;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantMenuResListDto {
    private List<RestaurantMenuInfoDto> restaurantMenuInfoDto;

    public RestaurantMenuResListDto(List<RestaurantMenuInfoDto> restaurantMenuInfoDto) {
        this.restaurantMenuInfoDto = restaurantMenuInfoDto;
    }
}
