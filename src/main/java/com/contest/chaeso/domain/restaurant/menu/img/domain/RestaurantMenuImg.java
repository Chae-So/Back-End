package com.contest.chaeso.domain.restaurant.menu.img.domain;

import com.contest.chaeso.domain.restaurant.menu.menu.domain.RestaurantMenu;
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
public class RestaurantMenuImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rt_menu_img_id")
    private Long rtMenuImgId;
    @NotNull
    @Column(length = 512)
    private String rtMenuImgLink;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "rt_menu_id")
    private RestaurantMenu restaurantMenu;


    @Builder
    private RestaurantMenuImg(String rtMenuImgLink, RestaurantMenu restaurantMenu) {
        this.rtMenuImgLink = rtMenuImgLink;
        this.restaurantMenu = restaurantMenu;
    }

    /**
     * 생성 메서드
     * @param rtMenuImgLink
     * @param restaurantMenu
     * @return
     */
    public static RestaurantMenuImg createRestaurantImg(String rtMenuImgLink, RestaurantMenu restaurantMenu) {
        return RestaurantMenuImg.builder()
                .rtMenuImgLink(rtMenuImgLink)
                .restaurantMenu(restaurantMenu)
                .build();
    }

    /**
     * restaurant cascade 생성
     * @param rtMenuImgLink
     * @return
     */
    public static RestaurantMenuImg createRestaurantImgWithCascade(String rtMenuImgLink) {
        return RestaurantMenuImg.builder()
                .rtMenuImgLink(rtMenuImgLink)
                .build();
    }

    public void setRestaurantMenu(RestaurantMenu restaurantMenu) {
        this.restaurantMenu = restaurantMenu;
    }
}
