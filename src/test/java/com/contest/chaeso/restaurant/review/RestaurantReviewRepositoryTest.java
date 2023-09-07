package com.contest.chaeso.restaurant.review;

import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import com.contest.chaeso.domain.restaurant.restaurant.domain.repository.RestaurantRepository;
import com.contest.chaeso.domain.restaurant.review.img.domain.RestaurantReviewImg;
import com.contest.chaeso.domain.restaurant.review.img.domain.repository.RestaurantReviewImgRepository;
import com.contest.chaeso.domain.restaurant.review.review.api.dto.res.RestaurantScoreCountInterface;
import com.contest.chaeso.domain.restaurant.review.review.domain.RestaurantReview;
import com.contest.chaeso.domain.restaurant.review.review.domain.repository.RestaurantReviewRepository;
import com.contest.chaeso.domain.users.users.domain.Users;
import com.contest.chaeso.domain.users.users.domain.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
public class RestaurantReviewRepositoryTest {

    @Autowired
    RestaurantReviewRepository restaurantReviewRepository;
    @Autowired
    RestaurantReviewImgRepository restaurantReviewImgRepository;

    @DisplayName("레스토랑 리뷰 리스트 가져오기")
    @Test
    public void getReviewListByRtId(){
        // given
        Long rtId = 1L;
        // when
        List<RestaurantReview> restaurantReviewByRestaurant = restaurantReviewRepository.findRestaurantReviewByRestaurant(rtId);
        assertThat(restaurantReviewByRestaurant.size()).isEqualTo(5); // review 개수
        assertThat(restaurantReviewByRestaurant.get(1).getRestaurantReviewImgList().size()).isEqualTo(3); // review 이미지 수

    }

    @DisplayName("레스토랑 리뷰 리스트 가져오기")
    @Test
    public void getRestaurantScoreCount(){
        // given
        Long rtId = 1L;

        // when
        List<RestaurantScoreCountInterface> restaurantScoreCount = restaurantReviewRepository.findRestaurantScoreCount(rtId);

        // then
        assertThat(restaurantScoreCount.get(0).getScore()).isEqualTo(2);
        assertThat(restaurantScoreCount.get(0).getCnt()).isEqualTo(2);
        assertThat(restaurantScoreCount.get(1).getScore()).isEqualTo(3);
        assertThat(restaurantScoreCount.get(1).getCnt()).isEqualTo(2);
        assertThat(restaurantScoreCount.get(2).getScore()).isEqualTo(4);
        assertThat(restaurantScoreCount.get(2).getCnt()).isEqualTo(1);

    }

    @DisplayName("review img 삭제 ")
    @Transactional
    @Test
    public void reviewImgDeleteTest(){
        // given
        Long rtReviewId = 65L;

        // when
        int i = restaurantReviewImgRepository.deleteByRestaurantReview(rtReviewId);

        // then
        assertThat(i).isEqualTo(2);

    }

}
