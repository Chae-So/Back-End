package com.contest.chaeso.admin.restaurant.application;

import com.contest.chaeso.admin.restaurant.controller.BzhDayEnum;
import com.contest.chaeso.admin.restaurant.controller.dto.RestaurantExcelDto;
import com.contest.chaeso.admin.restaurant.controller.dto.RestaurantMenuExcelDto;
import com.contest.chaeso.domain.restaurant.bzhour.domain.RestaurantBzh;
import com.contest.chaeso.domain.restaurant.img.domain.RestaurantImg;
import com.contest.chaeso.domain.restaurant.menu.domain.RestaurantMenu;
import com.contest.chaeso.domain.restaurant.restaurant.domain.Address;
import com.contest.chaeso.domain.restaurant.restaurant.domain.MealType;
import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import com.contest.chaeso.domain.restaurant.restaurant.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantExcelUploadService {

    private final RestaurantRepository restaurantRepository;

    public String saveRestaurantAndMenu(List<RestaurantExcelDto> restaurantExcelDtoList, List<RestaurantMenuExcelDto> restaurantMenuExcelDtoList) {

        for (RestaurantExcelDto restaurantExcelDto : restaurantExcelDtoList) {
            String rtName = restaurantExcelDto.getName();

            // mealtype 추가
            MealType mealType = new MealType(restaurantExcelDto.getForHere(), restaurantExcelDto.getToGo(), restaurantExcelDto.getDelivery());

            // address 추가
            Address address = new Address(restaurantExcelDto.getAddress(), restaurantExcelDto.getCorpLat(), restaurantExcelDto.getCorpLon());

            // restaurnt 생성
            Restaurant restaurant = Restaurant.createRestaurant(restaurantExcelDto.getName(), restaurantExcelDto.getCategory(), address, restaurantExcelDto.getPhoneNumber(), mealType);

            // restaurant img 추가
            RestaurantImg restaurantImgWithCascade = RestaurantImg.createRestaurantImgWithCascade(restaurantExcelDto.getRtImgLink(), 1);
            restaurant.addRestaurantImg(restaurantImgWithCascade);

            // bzh 추가
            String[] bzhArr = restaurantExcelDto.getBzh().split("\n");
            addRestaurantBzh(bzhArr, restaurant);


            // menu 추가
            for (RestaurantMenuExcelDto restaurantMenuExcelDto : restaurantMenuExcelDtoList) {
                if (rtName.equals(restaurantMenuExcelDto.getRtName())) {
                    RestaurantMenu restaurantMenuWithCascade = null;

                    if (restaurantMenuExcelDto.getImgLink().equals("null")) {
                        restaurantMenuWithCascade = RestaurantMenu.createRestaurantMenuWithCascade(restaurantMenuExcelDto.getMenuName(), restaurantMenuExcelDto.getPrice(), null);
                    }
                    else{
                        restaurantMenuWithCascade = RestaurantMenu.createRestaurantMenuWithCascade(restaurantMenuExcelDto.getMenuName(), restaurantMenuExcelDto.getPrice(), restaurantMenuExcelDto.getImgLink());
                    }
                    restaurant.addRestaurantMenu(restaurantMenuWithCascade);
                }
            }

            // restaurant 저장
            restaurantRepository.save(restaurant);

        }

        return "success";
    }


    private void addRestaurantBzh(String[] bzhArr, Restaurant restaurant){

        for (String bzh : bzhArr) {
            String[] line = bzh.split(" ");
            String day = line[0];
            String start = line[1];

            Integer days = null;
            LocalTime startTime = null;
            LocalTime endTime = null;

            switch (day){
                case "월" :
                    days = BzhDayEnum.MONDAY.getVal();
                    break;
                case "화" :
                    days = BzhDayEnum.TUESDAY.getVal();
                    break;
                case "수" :
                    days = BzhDayEnum.WEDNESDAY.getVal();
                    break;
                case "목" :
                    days = BzhDayEnum.THURSDAY.getVal();
                    break;
                case "금" :
                    days = BzhDayEnum.FRIDAY.getVal();
                    break;
                case "토" :
                    days = BzhDayEnum.SATURDAY.getVal();
                    break;
                case "일" :
                    days = BzhDayEnum.SUNDAY.getVal();
                    break;

            }

            if (!start.equals("정기휴무")) {
                String end = line[2];
                startTime = LocalTime.parse(start);
                endTime = LocalTime.parse(end);
            }

            RestaurantBzh restaurantBzhWithCascade = RestaurantBzh.createRestaurantBzhWithCascade(days, startTime, endTime, null, null);

            restaurant.addRestaurantBzh(restaurantBzhWithCascade);
        }
    }
}
