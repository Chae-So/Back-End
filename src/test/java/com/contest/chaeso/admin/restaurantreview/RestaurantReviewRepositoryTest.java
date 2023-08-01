package com.contest.chaeso.admin.restaurantreview;

import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import com.contest.chaeso.domain.restaurant.restaurant.domain.repository.RestaurantRepository;
import com.contest.chaeso.domain.restaurant.review.img.domain.RestaurantReviewImg;
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
    /**
     * SELECT * from users u; -- 5개
     * SELECT * from restaurant r; -- 6개
     * SELECT * from restaurant_review rr; -- 30개(5x6)
     * SELECT * from restaurant_review_img rri; -- 90개(30x3)
     */
    @DisplayName("레스토랑 리뷰 save test ")
    @Transactional
    @Test
    public void insertRestaurantReviewTest(){
        // given
        String reReviewImgLink = "TestLink";

        for(long i = 1; i <= 5; i++){
            Users user = usersRepository.findById(i).get();

            for (long j = 1; j <= 6; j++) {
                Restaurant restaurant = restaurantRepository.findById(j).get();

                int score = (int)((Math.random() * 10000) % 5) + 1;

                // restaurant review
                RestaurantReview restaurantReview = RestaurantReview.createRestaurantReview("title test", "contents test", score, user, restaurant);

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

    @Autowired
    RestaurantReviewRepository restaurantReviewRepository;
}
