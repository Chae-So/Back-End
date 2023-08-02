package com.contest.chaeso.domain.restaurant.review.review.api.dto.res;

import com.contest.chaeso.domain.restaurant.review.review.domain.RestaurantReview;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantReviewResDto {

    private Long rtReviewId;
    private String contents;
    private Integer score;
    private String company;
    private String companyVeganType;
    private Integer nonVeganFood;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;
    private String nickname;

    private List<RestaurantReviewImgResDto> restaurantReviewImgList;

    public RestaurantReviewResDto(RestaurantReview restaurantReview) {
        this.rtReviewId = restaurantReview.getRtReviewId();
        this.contents = restaurantReview.getContents();
        this.score = restaurantReview.getScore();
        this.company = restaurantReview.getCompany();
        this.companyVeganType = restaurantReview.getCompanyVeganType();
        this.nonVeganFood = restaurantReview.getNonVeganFood();
        this.createdAt = restaurantReview.getCreatedAt();
        this.updatedAt = restaurantReview.getUpdatedAt();
        this.nickname = restaurantReview.getUsers().getName(); /** nickname으로 바꿔야 함 */
        this.restaurantReviewImgList = restaurantReview.getRestaurantReviewImgList().stream().map(RestaurantReviewImgResDto::new).collect(Collectors.toList());
    }

}
