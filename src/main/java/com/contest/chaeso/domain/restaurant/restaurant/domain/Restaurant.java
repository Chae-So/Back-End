package com.contest.chaeso.domain.restaurant.restaurant.domain;

import com.contest.chaeso.domain.restaurant.bookmark.domain.RestaurantBookmark;
import com.contest.chaeso.domain.restaurant.bzhour.domain.RestaurantBzh;
import com.contest.chaeso.domain.restaurant.img.domain.RestaurantImg;
import com.contest.chaeso.domain.restaurant.menu.domain.RestaurantMenu;
import com.contest.chaeso.domain.restaurant.review.review.domain.RestaurantReview;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rt_id")
    private Long rtId;

    @NotNull
    private String name;

    @NotNull
    private String category;

    private String phoneNumber;

    @Embedded
    private Address address;
    @Embedded
    private MealType mealType;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<RestaurantBookmark> restaurantBookmarkList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<RestaurantMenu> restaurantMenuList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<RestaurantReview> restaurantReviewList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<RestaurantImg> restaurantImgList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<RestaurantBzh> restaurantBzhList = new ArrayList<>();

    @Builder
    private Restaurant(String name, String category, Address address, String phoneNumber, MealType mealType) {
        this.name = name;
        this.category = category;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mealType = mealType;
    }

    /**
     * 생성 메서드
     * @param category
     * @param address
     * @param mealType
     * @return
     */
    public static Restaurant createRestaurant(String name, String category, Address address, String phoneNumber,MealType mealType) {
        return Restaurant.builder()
                .name(name)
                .category(category)
                .address(address)
                .phoneNumber(phoneNumber)
                .mealType(mealType)
                .build();
    }

    /**
     * 양방향 연관관계, cascade persist
     */
    public void addRestaurantBookmark(RestaurantBookmark restaurantBookmark){
        if(restaurantBookmark.getRestaurant() != null){
            restaurantBookmark.getRestaurant().getRestaurantBookmarkList().remove(restaurantBookmark);
        }
        this.getRestaurantBookmarkList().add(restaurantBookmark);
        restaurantBookmark.setRestaurant(this);
    }

    public void addRestaurantMenu(RestaurantMenu restaurantMenu){
        if(restaurantMenu.getRestaurant() != null){
            restaurantMenu.getRestaurant().getRestaurantBookmarkList().remove(restaurantMenu);
        }
        restaurantMenu.setRestaurant(this);
        this.getRestaurantMenuList().add(restaurantMenu);
    }

    public void addRestaurantReview(RestaurantReview restaurantReview){
        if(restaurantReview.getRestaurant() != null){
            restaurantReview.getRestaurant().getRestaurantBookmarkList().remove(restaurantReview);
        }

        restaurantReview.setRestaurant(this);
        this.getRestaurantReviewList().add(restaurantReview);
    }

    public void addRestaurantImg(RestaurantImg restaurantImg){
        if(restaurantImg.getRestaurant() != null){
            restaurantImg.getRestaurant().getRestaurantBookmarkList().remove(restaurantImg);
        }
        restaurantImg.setRestaurant(this);
        this.getRestaurantImgList().add(restaurantImg);
    }

    public void addRestaurantBzh(RestaurantBzh restaurantBzh){
        if(restaurantBzh.getRestaurant() != null){
            restaurantBzh.getRestaurant().getRestaurantBookmarkList().remove(restaurantBzh);
        }
        restaurantBzh.setRestaurant(this);
        this.getRestaurantBzhList().add(restaurantBzh);
    }



}
