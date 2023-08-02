package com.contest.chaeso.admin.restaurant;

import com.contest.chaeso.domain.restaurant.bzhour.domain.RestaurantBzh;
import com.contest.chaeso.domain.restaurant.img.domain.RestaurantImg;
import com.contest.chaeso.domain.restaurant.menu.img.domain.RestaurantMenuImg;
import com.contest.chaeso.domain.restaurant.menu.menu.domain.RestaurantMenu;
import com.contest.chaeso.domain.restaurant.restaurant.domain.MealType;
import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import com.contest.chaeso.domain.restaurant.restaurant.domain.repository.RestaurantRepository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
public class RestaurantRepositoryTest {

    @Autowired
    RestaurantRepository restaurantRepository;

    /**
     * SELECT * from restaurant r; -- 6개
     * SELECT * from restaurant_bzh rb; -- 42개(6x7)
     * SELECT * from restaurant_img ri; -- 18개(6x3)
     * SELECT * from restaurant_menu rm; -- 18개(6x3)
     */
    @DisplayName("레스토랑 정보(영업시간, 이미지, 메뉴, 메뉴 이미지)save test")
    @Transactional
    @Test
    public void insertRestaurantTest(){
        // given
        String[] rtName = new String[]{"교촌치킨", "아웃백", "스타벅스", "커피빈", "이마트", "롯데마트"};
        String[] rtCategory = new String[]{"음식점", "음식점", "카페", "카페", "스토어", "스토어"};
        String[] rtAddress = new String[]{"서울시 가나다라동", "인천시 가나다라동", "부산시 가나다라동", "울산시 가나다라동", "성남시 가나다라동", "대구시 가나다라동"};
//        String reImgLink = "data:image/jpeg;base64,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";
        String reImgLink = "TestLink";
        String phoneNumber = "0507-0000-0000";

        for(int i = 0; i < 6; i++){
            int mealType = i % 2;
            // restaurant
            Restaurant restaurant = Restaurant.createRestaurant(
                    rtName[i],
                    rtCategory[i],
                    rtAddress[i],
                    phoneNumber,
                    new MealType(mealType, mealType, mealType));

            // restaurant menu
            RestaurantMenu rtMenu1 = RestaurantMenu.createRestaurantMenuWithCascade(rtName[i]+" 메뉴 테스트" + 1, 11000);
            RestaurantMenu rtMenu2 = RestaurantMenu.createRestaurantMenuWithCascade(rtName[i]+" 메뉴 테스트" + 2, 12000);
            RestaurantMenu rtMenu3 = RestaurantMenu.createRestaurantMenuWithCascade(rtName[i]+" 메뉴 테스트" + 3, 13000);

            // restaurant menu img
            RestaurantMenuImg rtMenuImg1 = RestaurantMenuImg.createRestaurantImgWithCascade("메뉴 "+reImgLink);
            RestaurantMenuImg rtMenuImg2 = RestaurantMenuImg.createRestaurantImgWithCascade("메뉴 "+reImgLink);
            RestaurantMenuImg rtMenuImg3  = RestaurantMenuImg.createRestaurantImgWithCascade("메뉴 "+reImgLink);
            rtMenu1.addRestaurantMenuImg(rtMenuImg1);
            rtMenu2.addRestaurantMenuImg(rtMenuImg2);
            rtMenu3.addRestaurantMenuImg(rtMenuImg3);

            restaurant.addRestaurantMenu(rtMenu1);
            restaurant.addRestaurantMenu(rtMenu2);
            restaurant.addRestaurantMenu(rtMenu3);

            // restaurant img
            RestaurantImg rtImg1 = RestaurantImg.createRestaurantImgWithCascade(reImgLink);
            RestaurantImg rtImg2 = RestaurantImg.createRestaurantImgWithCascade(reImgLink);
            RestaurantImg rtImg3 = RestaurantImg.createRestaurantImgWithCascade(reImgLink);
            restaurant.addRestaurantImg(rtImg1);
            restaurant.addRestaurantImg(rtImg2);
            restaurant.addRestaurantImg(rtImg3);

            // restaurant bzh
            LocalTime now = LocalTime.now();
            RestaurantBzh mon = RestaurantBzh.createRestaurantBzhWithCascade(1, now, now, now, now);
            RestaurantBzh tue = RestaurantBzh.createRestaurantBzhWithCascade(2, now, now, now, now);
            RestaurantBzh wed = RestaurantBzh.createRestaurantBzhWithCascade(3, now, now, now, now);
            RestaurantBzh thur = RestaurantBzh.createRestaurantBzhWithCascade(4, now, now, now, now);
            RestaurantBzh fri = RestaurantBzh.createRestaurantBzhWithCascade(5, now, now, now, now);
            RestaurantBzh sat = RestaurantBzh.createRestaurantBzhWithCascade(6, now, now, now, now);
            RestaurantBzh sun = RestaurantBzh.createRestaurantBzhWithCascade(7, now, now, now, now);
            restaurant.addRestaurantBzh(mon);
            restaurant.addRestaurantBzh(tue);
            restaurant.addRestaurantBzh(wed);
            restaurant.addRestaurantBzh(thur);
            restaurant.addRestaurantBzh(fri);
            restaurant.addRestaurantBzh(sat);
            restaurant.addRestaurantBzh(sun);

            // when-then
            restaurantRepository.save(restaurant);

        }

    }

    @DisplayName("메뉴 이미지 테스트")
    @Test
    public void menuImgSave(){
        // given

        // when

        // then

    }

}
