package com.contest.chaeso.domain.restaurant.img.domain;

import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RestaurantImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rt_img_id")
    private Long rtBMarkId;
    private String rtImgLink;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "rt_id")
    private Restaurant restaurant;

    @Builder
    private RestaurantImg(String rtImgLink, Restaurant restaurant) {
        this.rtImgLink = rtImgLink;
        this.restaurant = restaurant;
    }

    /**
     * 생성 메서드
     * @param rtImgLink
     * @param restaurant
     * @return
     */
    public static RestaurantImg createRestaurantImg(String rtImgLink, Restaurant restaurant) {
        return RestaurantImg.builder()
                .rtImgLink(rtImgLink)
                .restaurant(restaurant)
                .build();
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
