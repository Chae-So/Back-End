package com.contest.chaeso.domain.community.community.api.dto.res;

import com.contest.chaeso.domain.community.img.api.dto.res.ResponseImgListDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResponseCommunityListDto {

    private Long communityId;
    private String profilePicture;
    private String nickname;
    private Long likeCount;
    private Long reviewCount;

    @QueryProjection
    public ResponseCommunityListDto(Long communityId, String profilePicture, String nickname, Long likeCount, Long reviewCount) {
        this.communityId = communityId;
        this.profilePicture = profilePicture;
        this.nickname = nickname;
        this.likeCount = likeCount;
        this.reviewCount = reviewCount;
    }
}
