package com.contest.chaeso.admin.restaurantbookmark;

import com.contest.chaeso.domain.restaurant.bookmark.domain.RestaurantBookmark;
import com.contest.chaeso.domain.restaurant.bookmark.domain.repository.RestaurantBookmarkRepository;
import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import com.contest.chaeso.domain.restaurant.restaurant.domain.repository.RestaurantRepository;
import com.contest.chaeso.domain.restaurant.review.img.domain.RestaurantReviewImg;
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

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
public class RestaurantBookmarkRepositoryTest {

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RestaurantBookmarkRepository restaurantBookmarkRepository;


//    @DisplayName("유저 추가")
//    @Transactional
//    @Test
//    public void user(){
//        // given
//        for(int i = 1; i <= 5; i++){
//            Users users = new Users("test" + i);
//            usersRepository.save(users);
//        }
//
//    }

    /**
     * SELECT * from users u; -- 5개
     * SELECT * from restaurant r; -- 6개
     * SELECT * FROM restaurant_bookmark rb; -- 30개(5x6)
     *
     */
    @DisplayName("레스토랑 북마크 save test")
    @Transactional
    @Test
    public void insertRestaurantBookmarkTest(){
        //given

        for(long i = 1; i <= 5; i++){
            Users user = usersRepository.findById(i).get();

            for (long j = 1; j <= 6; j++) {
                saveBookmark(j, user);
            }

        }

    }

    @DisplayName("짝수 삭제")
    @Transactional
    @Test
    public void deleteRestaurantBookmarkTest(){
        // given
        for(long i = 1; i <= 30; i++){
            long temp = i % 2;
            if(temp == 0){
                restaurantBookmarkRepository.deleteById(i);

            }
        }

    }

    private void saveBookmark(long j, Users user){
        Restaurant restaurant = restaurantRepository.findById(j).get();

        RestaurantBookmark restaurantBookmark = RestaurantBookmark.createRestaurantBookmark(user, restaurant);

        restaurantBookmarkRepository.save(restaurantBookmark);
    }


}
