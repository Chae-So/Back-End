package com.contest.chaeso.domain.restaurant.restaurant.domain;

import com.contest.chaeso.domain.common.BaseTimeEntity;
import com.contest.chaeso.domain.restaurant.bookmark.domain.RestaurantBookmark;
import com.contest.chaeso.domain.restaurant.bzhour.domain.RestaurantBzh;
import com.contest.chaeso.domain.restaurant.img.domain.RestaurantImg;
import com.contest.chaeso.domain.restaurant.menu.domain.RestaurantMenu;
import com.contest.chaeso.domain.restaurant.review.review.domain.RestaurantReview;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Restaurant extends BaseTimeEntity { // 생성, 수정 시간 있어야할 듯

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rt_id")
    private Long rtId;

    private String category; // 이거 enum으로

    private String address; // 경기도 성남시 분당구 판교역로 166

    @Embedded
    private MealType mealType;

    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<RestaurantBookmark> restaurantBookmarkList = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<RestaurantMenu> restaurantMenuList = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<RestaurantReview> restaurantReviewList = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<RestaurantImg> restaurantImgList = new ArrayList<>();
    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<RestaurantBzh> restaurantBzhList = new ArrayList<>();

    @Builder
    private Restaurant(String category, String address, MealType mealType) {
        this.category = category;
        this.address = address;
        this.mealType = mealType;
    }

    /**
     * 생성 메서드
     * @param category
     * @param address
     * @param mealType
     * @return
     */
    private static Restaurant createRestaurant(String category, String address, MealType mealType) {
        return Restaurant.builder()
                .category(category)
                .address(address)
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
        this.getRestaurantMenuList().add(restaurantMenu);
        restaurantMenu.setRestaurant(this);
    }

    public void addRestaurantReview(RestaurantReview restaurantReview){
        if(restaurantReview.getRestaurant() != null){
            restaurantReview.getRestaurant().getRestaurantBookmarkList().remove(restaurantReview);
        }
        this.getRestaurantReviewList().add(restaurantReview);
        restaurantReview.setRestaurant(this);
    }

    public void addRestaurantImg(RestaurantImg restaurantImg){
        if(restaurantImg.getRestaurant() != null){
            restaurantImg.getRestaurant().getRestaurantBookmarkList().remove(restaurantImg);
        }
        this.getRestaurantImgList().add(restaurantImg);
        restaurantImg.setRestaurant(this);
    }

    public void addRestaurantBzh(RestaurantBzh restaurantBzh){
        if(restaurantBzh.getRestaurant() != null){
            restaurantBzh.getRestaurant().getRestaurantBookmarkList().remove(restaurantBzh);
        }
        this.getRestaurantBzhList().add(restaurantBzh);
        restaurantBzh.setRestaurant(this);
    }



}
