package com.contest.chaeso.domain.restaurant.bookmark.domain;


import com.contest.chaeso.domain.common.BaseTimeEntity;
import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import com.contest.chaeso.domain.users.users.domain.Users;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RestaurantBookmark extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rt_bmark_id")
    private Long rtBMarkId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "rt_id")
    private Restaurant restaurant;

    @Builder
    private RestaurantBookmark(Users users, Restaurant restaurant) {
        this.users = users;
        this.restaurant = restaurant;
    }

    /**
     * 생성 메서드
     * @param users
     * @param restaurant
     * @return
     */
    public static RestaurantBookmark createRestaurantBookmark(Users users, Restaurant restaurant){
        return RestaurantBookmark.builder()
                .users(users)
                .restaurant(restaurant)
                .build();
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }



}
