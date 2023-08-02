package com.contest.chaeso.domain.restaurant.restaurant.application;

import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.RestaurantInfoResDto;
import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.RestaurantMainInfoResInterface;
import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.RestaurantMainInfoListResDto;
import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import com.contest.chaeso.domain.restaurant.restaurant.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    /**
     * main restaurant List 가져오기
     * @param userId
     * @return
     */
    public RestaurantMainInfoListResDto getRestaurantMainInfoList(Long userId){
        int today = getToday();
        List<RestaurantMainInfoResInterface> restaurantInfo = restaurantRepository.findRestaurantMainInfoList(userId, today);
        RestaurantMainInfoListResDto mainResponseDto = new RestaurantMainInfoListResDto(restaurantInfo);

        return mainResponseDto;
    }

    /**
     * category  main restaurant  List 가져오기
     * @param userId
     * @return
     */
    public RestaurantMainInfoListResDto getRestaurantMainInfoByCategoryList(Long userId, String category){
        int today = getToday();
        List<RestaurantMainInfoResInterface> restaurantInfo = restaurantRepository.findRestaurantMainInfoByCategoryList(userId, today, category);


        RestaurantMainInfoListResDto mainResponseDto = new RestaurantMainInfoListResDto(restaurantInfo);

        return mainResponseDto;
    }


    /**
     * main restaurant info 가져오기
     * @return
     */
    public RestaurantInfoResDto findRestaurantInfo(Long rtId){
        Restaurant restaurant = restaurantRepository.findRestaurantInfoByRtId(rtId);
        RestaurantInfoResDto restaurantInfoResDto = new RestaurantInfoResDto(restaurant);

        return restaurantInfoResDto;
    }


    private int getToday(){
        LocalDate now = LocalDate.now();

        return now.getDayOfWeek().getValue();
    }

    private void haveRestaurntMainImg(){

    }

}

