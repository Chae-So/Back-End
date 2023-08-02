package com.contest.chaeso.domain.community.review.review.api.dto.req;

import com.contest.chaeso.domain.community.community.domain.Community;
import com.contest.chaeso.domain.community.review.review.domain.CommunityReview;
import com.contest.chaeso.domain.users.users.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RequestCommunityReviewWriteFormDto {

    private Long communityId;
    private String nickname;
    private String content;

    public CommunityReview toEntity(Users users, Community community) {
        return CommunityReview.builder()
                .users(users)
                .contents(content)
                .writer(nickname)
                .community(community)
                .build();
    }
}
