package com.contest.chaeso.domain.community.review.review.api.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseCommunityReviewListDto {

    private String reviewerPicture;
    private String reviewerNickname;
    private String reviewScore;
    private Integer score;
}
