package com.contest.chaeso.restaurant.restaurant;


import com.contest.chaeso.domain.restaurant.bzhour.domain.RestaurantBzh;
import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.RestaurantMainInfoResInterface;
import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import com.contest.chaeso.domain.restaurant.restaurant.domain.repository.RestaurantRepository;
import com.contest.chaeso.domain.restaurant.review.img.domain.RestaurantReviewImg;
import com.contest.chaeso.domain.restaurant.review.review.api.dto.req.RestaurantReviewReqDto;
import com.contest.chaeso.domain.restaurant.review.review.domain.RestaurantReview;
import com.contest.chaeso.domain.users.users.domain.Users;
import com.contest.chaeso.domain.users.users.domain.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
public class RestaurantRepositoryTest {


    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    UsersRepository usersRepository;

    @DisplayName("restaurant 메인 가게 정보 list 가져오기")
    @Test
    public void getRestaurantInfoListTest(){
        // given
        Long userId = 2L;
        int days = 1; // 1 : 월요일, 7 : 일요일

        // when
        List<RestaurantMainInfoResInterface> restaurantMainInfoList = restaurantRepository.findRestaurantMainInfoList(userId, days);

        // then
        assertThat(restaurantMainInfoList.size()).isEqualTo(6);

    }

    @DisplayName("restaurnt 정보 탭 가져오기")
    @Test
    public void findRestaurantInfoByRtIdTest(){
        // given
        Long rtId = 4L;

        // when
        Restaurant restaurant = restaurantRepository.findRestaurantInfoByRtId(rtId);

        // then
        List<RestaurantBzh> restaurantBzhList = restaurant.getRestaurantBzhList();
        assertThat(restaurantBzhList.size()).isEqualTo(7);

    }

    @DisplayName("restaurant에서 review save Test")
    @Transactional
    @Test
    public void saveReviewTest(){
        // given
        Restaurant restaurant = restaurantRepository.findById(2L).get();
        Users user = usersRepository.findById(1L).get();
        RestaurantReview restaurantReview = RestaurantReview.createRestaurantReview(new RestaurantReviewReqDto("restaurnt에서 review save test", 5, "혼자서", "비건", 1), user, restaurant);
        RestaurantReviewImg restaurantReviewImg = RestaurantReviewImg.createRestaurantReviewImgWithCascade("restaurant에서 review save test");
        RestaurantReviewImg restaurantReviewImg2 = RestaurantReviewImg.createRestaurantReviewImgWithCascade("222restaurant에서 review save test");

        // when
        restaurantReview.addRestaurantReviewImg(restaurantReviewImg);
        restaurantReview.addRestaurantReviewImg(restaurantReviewImg2);
        restaurant.addRestaurantReview(restaurantReview);
        restaurantRepository.save(restaurant);

        // then

    }

}
