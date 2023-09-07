package com.contest.chaeso.domain.restaurant.menu.domain;

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
public class RestaurantMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rt_menu_id")
    private Long rtMenuId;
    @NotNull
    private String menuName;
    @NotNull
    private int price;

    private String menuImgLink;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "rt_id")
    private Restaurant restaurant;


    @Builder
    private RestaurantMenu(String menuName, int price, String menuImgLink, Restaurant restaurant) {
        this.menuName = menuName;
        this.price = price;
        this.menuImgLink = menuImgLink;
        this.restaurant = restaurant;
    }

    /**
     * 생성 메서드
     * @param menuName
     * @param price
     * @param restaurant
     * @return
     */
    public static RestaurantMenu createRestaurantMenu(String menuName, int price, String menuImgLink, Restaurant restaurant){
        return RestaurantMenu.builder()
                .menuName(menuName)
                .price(price)
                .menuImgLink(menuImgLink)
                .restaurant(restaurant)
                .build();

    }

    /**
     * restaurant cascade 생성
     * @param menuName
     * @param price
     * @return
     */
    public static RestaurantMenu createRestaurantMenuWithCascade(String menuName, int price, String menuImgLink){
        return RestaurantMenu.builder()
                .menuName(menuName)
                .price(price)
                .menuImgLink(menuImgLink)
                .build();

    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }



}
