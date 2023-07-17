package com.contest.chaeso.domain.community.community.api.dto.res;

import com.contest.chaeso.domain.community.img.domain.CommunityImg;
import com.contest.chaeso.domain.community.like.domain.CommunityLike;
import com.contest.chaeso.domain.community.review.review.domain.CommunityReview;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseCommunityListDto {

    private Long communityId;
    private String userId;
    private CommunityImg communityImg;
    private CommunityLike communityLike;
    private CommunityReview communityReview;

}
