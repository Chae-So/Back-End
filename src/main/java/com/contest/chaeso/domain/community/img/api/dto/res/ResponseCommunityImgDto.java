package com.contest.chaeso.domain.community.img.api.dto.res;

import com.contest.chaeso.domain.community.img.domain.CommunityImg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseCommunityImgDto {

    private Long communityId;
    private Long coImgId;
    private String imgUrl;

    public static ResponseCommunityImgDto communityImgList(CommunityImg communityImg) {
        return ResponseCommunityImgDto.builder()
                .communityId(communityImg.getCommunity().getId())
                .coImgId(communityImg.getId())
                .imgUrl(communityImg.getCoImgLink())
                .build();
    }
}
