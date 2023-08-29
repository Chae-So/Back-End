package com.contest.chaeso.admin.restaurant.application;

import com.contest.chaeso.admin.restaurant.controller.dto.RestaurantExcelDto;
import com.contest.chaeso.admin.restaurant.controller.dto.RestaurantMenuExcelDto;
import com.contest.chaeso.domain.restaurant.restaurant.domain.Address;
import com.contest.chaeso.domain.restaurant.restaurant.domain.MealType;
import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import com.contest.chaeso.domain.restaurant.restaurant.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantExcelUploadService {

    private final RestaurantRepository restaurantRepository;

    public String saveRestaurantAndMEnu(List<RestaurantExcelDto> restaurantExcelDtoList, List<RestaurantMenuExcelDto> restaurantMenuExcelDtoList) {

        for (RestaurantExcelDto restaurantExcelDto : restaurantExcelDtoList) {
            String rtName = restaurantExcelDto.getName();
            MealType mealType = new MealType(restaurantExcelDto.getForHere(), restaurantExcelDto.getToGo(), restaurantExcelDto.getDelivery());
            Address address = new Address(restaurantExcelDto.getAddress(), restaurantExcelDto.getCorpLat(), restaurantExcelDto.getCorpLon());
            Restaurant restaurant = Restaurant.createRestaurant(restaurantExcelDto.getName(), restaurantExcelDto.getCategory(), address, restaurantExcelDto.getPhoneNumber(), mealType);

            // bzh
            String bzh = restaurantExcelDto.getBzh();

//            restaurant.addRestaurantBzh();

            // menu
            for (RestaurantMenuExcelDto restaurantMenuExcelDto : restaurantMenuExcelDtoList) {
                if (rtName.equals(restaurantMenuExcelDto.getRtName())) {
//                    restaurant.addRestaurantMenu();
                }
            }

            restaurantRepository.save(restaurant);

        }
        // address
        // meal type
        // bzh 따로 빼기



        return "success";
    }
}
