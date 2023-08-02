package com.contest.chaeso.domain.restaurant.restaurant.api.dto.res;

import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantInfoResDto {

    private int forHere;
    private int toGo;
    private int delivery;
    private String address;
    private List<RestaurantBzhResDto> restaurantBzh;

    public RestaurantInfoResDto(Restaurant restaurant) {
        this.forHere = restaurant.getMealType().getForHere();
        this.toGo = restaurant.getMealType().getToGo();
        this.delivery = restaurant.getMealType().getDelivery();
        this.address = restaurant.getAddress();
        this.restaurantBzh = restaurant.getRestaurantBzhList().stream().map(RestaurantBzhResDto::new).collect(Collectors.toList());

    }

}
