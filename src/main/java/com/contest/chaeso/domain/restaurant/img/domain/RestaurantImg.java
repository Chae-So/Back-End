package com.contest.chaeso.domain.restaurant.img.domain;

import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RestaurantImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rt_img_id")
    private Long rtBMarkId;
    @NotNull
    @Column(length = 512)
    private String rtImgLink;
    private int flag;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "rt_id")
    private Restaurant restaurant;

    @Builder
    private RestaurantImg(String rtImgLink, int flag, Restaurant restaurant) {
        this.rtImgLink = rtImgLink;
        this.flag = flag;
        this.restaurant = restaurant;
    }

    /**
     * 생성 메서드
     * @param rtImgLink
     * @param restaurant
     * @return
     */
    public static RestaurantImg createRestaurantImg(String rtImgLink, int flag, Restaurant restaurant) {
        return RestaurantImg.builder()
                .rtImgLink(rtImgLink)
                .flag(flag)
                .restaurant(restaurant)
                .build();
    }

    /**
     * restaurant cascade 생성
     * @param rtImgLink
     * @return
     */
    public static RestaurantImg createRestaurantImgWithCascade(String rtImgLink, int flag) {
        return RestaurantImg.builder()
                .rtImgLink(rtImgLink)
                .flag(flag)
                .build();
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
