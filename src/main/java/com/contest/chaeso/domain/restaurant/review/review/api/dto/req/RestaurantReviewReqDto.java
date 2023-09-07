package com.contest.chaeso.domain.restaurant.review.review.api.dto.req;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantReviewReqDto {

    private String contents;
    private Integer score;
    private String company;
    private String companyVeganType;
    private Integer nonVeganFood;

    public RestaurantReviewReqDto(String contents, Integer score, String company, String companyVeganType, Integer nonVeganFood) {
        this.contents = contents;
        this.score = score;
        this.company = company;
        this.companyVeganType = companyVeganType;
        this.nonVeganFood = nonVeganFood;
    }
}
