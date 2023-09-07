package com.contest.chaeso.admin.restaurantreview;

import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import com.contest.chaeso.domain.restaurant.restaurant.domain.repository.RestaurantRepository;
import com.contest.chaeso.domain.restaurant.review.img.domain.RestaurantReviewImg;
import com.contest.chaeso.domain.restaurant.review.review.api.dto.req.RestaurantReviewReqDto;
import com.contest.chaeso.domain.restaurant.review.review.domain.RestaurantReview;
import com.contest.chaeso.domain.restaurant.review.review.domain.repository.RestaurantReviewRepository;
import com.contest.chaeso.domain.users.users.domain.Users;
import com.contest.chaeso.domain.users.users.domain.repository.UsersRepository;
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
public class RestaurantReviewRepositoryTest {

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    RestaurantReviewRepository restaurantReviewRepository;

    @DisplayName("레스토랑 리뷰 save test ")
//    @Transactional
    @Test
    public void insertRestaurantReviewTest(){
        // given
        String reReviewImgLink = "Review Img TestLink";

        for(long i = 1; i <= 5; i++){
            Users user = usersRepository.findById(i).get();

            for (long j = 1; j <= 6; j++) {
                Restaurant restaurant = restaurantRepository.findById(j).get();

                int score = (int)((Math.random() * 10000) % 5) + 1;

                // restaurant review
                RestaurantReview restaurantReview = RestaurantReview.createRestaurantReview(new RestaurantReviewReqDto("contents test", score, "혼자서", "비건", 1), user, restaurant);

                // restaurant review img
                RestaurantReviewImg rtReImg1 = RestaurantReviewImg.createRestaurantReviewImgWithCascade(reReviewImgLink);
                RestaurantReviewImg rtReImg2 = RestaurantReviewImg.createRestaurantReviewImgWithCascade(reReviewImgLink);
                RestaurantReviewImg rtReImg3 = RestaurantReviewImg.createRestaurantReviewImgWithCascade(reReviewImgLink);
                restaurantReview.addRestaurantReviewImg(rtReImg1);
                restaurantReview.addRestaurantReviewImg(rtReImg2);
                restaurantReview.addRestaurantReviewImg(rtReImg3);

                // when-then
                restaurantReviewRepository.save(restaurantReview);
            }


        }


    }


    @Test
    @Transactional
    public void reviewNonVeganUpdateTest(){
        // given
        for(long i = 31; i <= 60; i++){
            RestaurantReview restaurantReview = restaurantReviewRepository.findById(i).get();
            restaurantReview.updateNonVeganFood((int)i % 2);
            restaurantReviewRepository.save(restaurantReview); // update 쿼리 나감
        }
        // when

        // then

    }

    @DisplayName("review에서 review save Test")
    @Transactional
    @Test
    public void saveReviewTest(){
        // given
        Long userId = 1L;
        Long rtId = 1L;
        Restaurant restaurant = restaurantRepository.findById(rtId).get();
        Users user = usersRepository.findById(userId).get();
        RestaurantReview restaurantReview = RestaurantReview.createRestaurantReview(new RestaurantReviewReqDto("restaurant Review에서 save test", 5, "혼자서", "비건", 1), user, restaurant);
        RestaurantReviewImg restaurantReviewImg = RestaurantReviewImg.createRestaurantReviewImgWithCascade("111restaurant Review에서 save test");
        RestaurantReviewImg restaurantReviewImg2 = RestaurantReviewImg.createRestaurantReviewImgWithCascade("222restaurant Review에서 save test");

        // when
        restaurantReview.addRestaurantReviewImg(restaurantReviewImg);
        restaurantReview.addRestaurantReviewImg(restaurantReviewImg2);
        restaurantReviewRepository.save(restaurantReview);

        // then

    }

}
