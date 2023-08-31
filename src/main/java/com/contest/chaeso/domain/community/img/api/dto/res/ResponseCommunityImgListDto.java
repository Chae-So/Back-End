package com.contest.chaeso.domain.community.img.api.dto.res;

import com.contest.chaeso.domain.community.img.domain.CommunityImg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ResponseCommunityImgListDto {

    private Long communityId;
    private Long coImgId;
    private String imgUrl;

    public static ResponseCommunityImgListDto communityImgList(CommunityImg communityImg) {
        return ResponseCommunityImgListDto.builder()
                .communityId(communityImg.getCommunity().getId())
                .coImgId(communityImg.getId())
                .imgUrl(communityImg.getCoImgLink())
                .build();
    }
}
