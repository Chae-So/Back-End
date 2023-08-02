package com.contest.chaeso.domain.restaurant.menu.menu.api.dto.res;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantMenuResListDto {
    private List<RestaurantMenuInfoResDto> restaurantMenuInfoDto;

    public RestaurantMenuResListDto(List<RestaurantMenuInfoResDto> restaurantMenuInfoDto) {
        this.restaurantMenuInfoDto = restaurantMenuInfoDto;
    }
}
