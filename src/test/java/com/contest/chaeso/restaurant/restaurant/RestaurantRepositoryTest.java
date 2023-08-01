package com.contest.chaeso.restaurant.restaurant;


import com.contest.chaeso.domain.restaurant.restaurant.domain.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
public class RestaurantRepositoryTest {


    @Autowired
    RestaurantRepository restaurantRepository;

    @DisplayName("restaurant 가게들 및 정보 list 가져오기")
    @Transactional
    @Test
    public void getRestaurantInfoListTest(){
        // given

        restaurantRepository.findRestaurantInfoByUserId(1L);
        // when

        // then

    }

}
