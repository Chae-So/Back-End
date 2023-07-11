package com.contest.chaeso.domain.restaurant.menu.domain;

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
public class RestaurantMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rt_menu_id")
    private Long rtMenuId;
    private String menuName;
    private int price;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "rt_id")
    private Restaurant restaurant;

    @Builder
    private RestaurantMenu(String menuName, int price, Restaurant restaurant) {
        this.menuName = menuName;
        this.price = price;
        this.restaurant = restaurant;
    }

    /**
     * 생성 메서드
     * @param menuName
     * @param price
     * @param restaurant
     * @return
     */
    public static RestaurantMenu createRestaurantMenu(String menuName, int price, Restaurant restaurant){
        return RestaurantMenu.builder()
                .menuName(menuName)
                .price(price)
                .restaurant(restaurant)
                .build();

    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


}
