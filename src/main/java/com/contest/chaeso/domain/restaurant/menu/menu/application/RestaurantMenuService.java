package com.contest.chaeso.domain.restaurant.menu.menu.application;

import com.contest.chaeso.domain.restaurant.menu.menu.api.dto.res.RestaurantMenuInfoResDto;
import com.contest.chaeso.domain.restaurant.menu.menu.api.dto.res.RestaurantMenuResListDto;
import com.contest.chaeso.domain.restaurant.menu.menu.domain.RestaurantMenu;
import com.contest.chaeso.domain.restaurant.menu.menu.domain.repository.RestaurantMenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class RestaurantMenuService {

    private final RestaurantMenuRepository restaurantMenuRepository;

    public RestaurantMenuResListDto findRestaurantMenuByRestaurant(Long rtId){
        List<RestaurantMenu> restaurantMenuByRestaurant = restaurantMenuRepository.findRestaurantMenuByRestaurantId(rtId);
        List<RestaurantMenuInfoResDto> collect = restaurantMenuByRestaurant.stream().map(RestaurantMenuInfoResDto::new).collect(Collectors.toList());
        RestaurantMenuResListDto restaurantMenuResListDto = new RestaurantMenuResListDto(collect);

        return restaurantMenuResListDto;

    }
}
