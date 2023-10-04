package com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info;

import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantInfoDto {

    private int forHere;
    private int toGo;
    private int delivery;
    private String address;
    private String phoneNumber;
    private List<RestaurantBzhDto> restaurantBzh;

    public RestaurantInfoDto(Restaurant restaurant) {
        this.forHere = restaurant.getMealType().getForHere();
        this.toGo = restaurant.getMealType().getToGo();
        this.delivery = restaurant.getMealType().getDelivery();
        this.address = restaurant.getAddress().getAddress();
        this.phoneNumber = restaurant.getPhoneNumber();
        this.restaurantBzh = restaurant.getRestaurantBzhList().stream().map(RestaurantBzhDto::new).collect(Collectors.toList());

    }

}
