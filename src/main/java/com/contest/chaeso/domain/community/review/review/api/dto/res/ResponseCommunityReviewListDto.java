package com.contest.chaeso.domain.community.review.review.api.dto.res;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ResponseCommunityReviewListDto {

    private Long reviewUserId;
    private String reviewerPicture;
    private String reviewerNickname;
    private Integer score;

    @QueryProjection
    public ResponseCommunityReviewListDto(Long reviewUserId, String reviewerPicture, String reviewerNickname, Integer score) {
        this.reviewUserId = reviewUserId;
        this.reviewerPicture = reviewerPicture;
        this.reviewerNickname = reviewerNickname;
        this.score = score;
    }
}
