package com.contest.chaeso.domain.restaurant.review.review.domain;

import com.contest.chaeso.domain.common.BaseTimeEntity;
import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import com.contest.chaeso.domain.restaurant.review.img.domain.RestaurantReviewImg;
import com.contest.chaeso.domain.restaurant.review.review.api.dto.req.RestaurantReviewReqDto;
import com.contest.chaeso.domain.users.users.domain.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String contents;
    @NotNull
    private Integer score;
    private String company;
    private String companyVeganType;
    private Integer nonVeganFood;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "rt_id")
    private Restaurant restaurant;

    @JsonManagedReference
    @OneToMany(mappedBy = "restaurantReview", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<RestaurantReviewImg> restaurantReviewImgList = new ArrayList<>();

    @Builder
    private RestaurantReview(String contents, int score, String company, String companyVeganType, int nonVeganFood, Users users, Restaurant restaurant) {
        this.contents = contents;
        this.score = score;
        this.company = company;
        this.companyVeganType = companyVeganType;
        this.nonVeganFood = nonVeganFood;
        this.users = users;
        this.restaurant = restaurant;
    }

    /**
     * 생성 메서드
     * @param restaurantReviewReqDto
     * @param users
     * @param restaurant
     * @return
     */
    public static RestaurantReview createRestaurantReview(RestaurantReviewReqDto restaurantReviewReqDto, Users users, Restaurant restaurant){
        return RestaurantReview.builder()
                .contents(restaurantReviewReqDto.getContents())
                .score(restaurantReviewReqDto.getScore())
                .company(restaurantReviewReqDto.getCompany())
                .companyVeganType(restaurantReviewReqDto.getCompanyVeganType())
                .nonVeganFood(restaurantReviewReqDto.getNonVeganFood())
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
        restaurantReviewImg.setRestaurantReview(this);
        this.getRestaurantReviewImgList().add(restaurantReviewImg);
    }

    public void updateNonVeganFood(int nonVeganFood){
        this.nonVeganFood = nonVeganFood;
    }

    public void updateReview(RestaurantReviewReqDto restaurantReviewReqDto, List<RestaurantReviewImg> files){
        this.contents = restaurantReviewReqDto.getContents();
        this.score = restaurantReviewReqDto.getScore();
        this.company = restaurantReviewReqDto.getCompany();
        this.companyVeganType = restaurantReviewReqDto.getCompanyVeganType();
        this.nonVeganFood = restaurantReviewReqDto.getNonVeganFood();

        for (RestaurantReviewImg file : files) {
            this.addRestaurantReviewImg(file);
        }

    }

}
