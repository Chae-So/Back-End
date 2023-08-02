package com.contest.chaeso.domain.restaurant.menu.menu.api.dto;

import com.contest.chaeso.domain.restaurant.menu.menu.api.dto.res.RestaurantMenuResListDto;
import com.contest.chaeso.domain.restaurant.menu.menu.application.RestaurantMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantMenuController {

    private final RestaurantMenuService restaurantMenuService;

    @GetMapping("/{rtId}/menu")
    public ResponseEntity<RestaurantMenuResListDto> getRestaurantMenuInfo(@PathVariable(name = "rtId") Long rtId){

        RestaurantMenuResListDto restaurantMenuByRestaurant = restaurantMenuService.findRestaurantMenuByRestaurant(rtId);

        return new ResponseEntity<>(restaurantMenuByRestaurant, HttpStatus.OK);
    }
}
