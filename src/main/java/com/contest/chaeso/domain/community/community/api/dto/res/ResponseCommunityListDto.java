package com.contest.chaeso.domain.community.community.api.dto.res;

import com.contest.chaeso.domain.community.img.domain.CommunityImg;
import com.contest.chaeso.domain.community.like.domain.CommunityLike;
import com.contest.chaeso.domain.community.review.review.domain.CommunityReview;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ResponseCommunityListDto {

    private Long communityId;
    private String profilePicture;
    private String nickname;
    private String communityImg;
    private Long likeCount;
    private Long reviewCount;

    @QueryProjection
    public ResponseCommunityListDto(Long communityId, String profilePicture, String nickname, String communityImg, Long likeCount, Long reviewCount) {
        this.communityId = communityId;
        this.profilePicture = profilePicture;
        this.nickname = nickname;
        this.communityImg = communityImg;
        this.likeCount = likeCount;
        this.reviewCount = reviewCount;
    }
}
