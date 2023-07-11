package com.contest.chaeso.domain.restaurant.review.img.domain;

import com.contest.chaeso.domain.restaurant.review.review.domain.RestaurantReview;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RestaurantReviewImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rt_review_img_id")
    private Long rtReviewImgId;

    private String rtReviewImgLink;

    // user_id, rt_id 필요없음 db 삭제하기
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "rt_review_id")
    private RestaurantReview restaurantReview;


    @Builder
    private RestaurantReviewImg(String rtReviewImgLink, RestaurantReview restaurantReview) {
        this.rtReviewImgLink = rtReviewImgLink;
        this.restaurantReview = restaurantReview;
    }

    /**
     * 생성 메서드
     * @param rtReviewImgLink
     * @param restaurantReview
     * @return
     */
    public static RestaurantReviewImg createRestaurantReviewImg(String rtReviewImgLink, RestaurantReview restaurantReview){
        return RestaurantReviewImg.builder()
                .rtReviewImgLink(rtReviewImgLink)
                .restaurantReview(restaurantReview)
                .build();
    }

    public void setRestaurantReview(RestaurantReview restaurantReview) {
        this.restaurantReview = restaurantReview;
    }
}
