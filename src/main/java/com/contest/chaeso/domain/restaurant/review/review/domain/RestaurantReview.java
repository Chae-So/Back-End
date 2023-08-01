package com.contest.chaeso.domain.restaurant.review.review.domain;

import com.contest.chaeso.domain.common.BaseTimeEntity;
import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import com.contest.chaeso.domain.restaurant.review.img.domain.RestaurantReviewImg;
import com.contest.chaeso.domain.users.users.domain.Users;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RestaurantReview extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rt_review_id")
    private Long rtReviewId;
    @NotNull
    private String title;
    @NotNull
    private String contents;
    @NotNull
    private int score;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "rt_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "restaurantReview", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<RestaurantReviewImg> restaurantReviewImgList = new ArrayList<>();

    @Builder
    private RestaurantReview(String title, String contents, int score, Users users, Restaurant restaurant) {
        this.title = title;
        this.contents = contents;
        this.score = score;
        this.users = users;
        this.restaurant = restaurant;
    }

    /**
     * 생성 메서드
     * @param title
     * @param contents
     * @param score
     * @param users
     * @param restaurant
     * @return
     */
    public static RestaurantReview createRestaurantReview(String title, String contents, int score, Users users, Restaurant restaurant){
        return RestaurantReview.builder()
                .title(title)
                .contents(contents)
                .score(score)
                .users(users)
                .restaurant(restaurant)
                .build();

    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * 양방향 연관관계, cascade persist
     */
    public void addRestaurantReviewImg(RestaurantReviewImg restaurantReviewImg){
        if (restaurantReviewImg.getRestaurantReview() != null) {
            restaurantReviewImg.getRestaurantReview().getRestaurantReviewImgList().remove(restaurantReviewImg);
        }
        this.getRestaurantReviewImgList().add(restaurantReviewImg);
        restaurantReviewImg.setRestaurantReview(this);
    }

}
