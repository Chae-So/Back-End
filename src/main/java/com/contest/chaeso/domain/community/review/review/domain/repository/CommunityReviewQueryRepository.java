package com.contest.chaeso.domain.community.review.review.domain.repository;

import com.contest.chaeso.domain.community.review.review.api.dto.res.ResponseCommunityReviewListDto;

import java.util.List;

public interface CommunityReviewQueryRepository {
    List<ResponseCommunityReviewListDto> findCommunityReviewListByCommunityId(Long communityId);

    ResponseCommunityReviewListDto findCommunityReviewOneByCommunityId(Long communityId);
}
