package com.contest.chaeso.domain.community.review.like.api.dto.req;

import com.contest.chaeso.domain.community.community.domain.Community;
import com.contest.chaeso.domain.community.like.domain.CommunityLike;
import com.contest.chaeso.domain.users.users.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RequestCommunityLikeDto {

    private String nickname;
    private Long communityId;

    public CommunityLike toEntity(Community community, Users users) {
        return CommunityLike.builder()
                .community(community)
                .users(users)
                .build();
    }

}
