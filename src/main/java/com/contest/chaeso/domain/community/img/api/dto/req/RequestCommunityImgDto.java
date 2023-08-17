package com.contest.chaeso.domain.community.img.api.dto.req;

import com.contest.chaeso.domain.community.community.domain.Community;
import com.contest.chaeso.domain.community.img.domain.CommunityImg;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestCommunityImgDto {

    private Community community;
    private String imgUrl;

    public CommunityImg toEntity() {
        return CommunityImg.builder()
                .community(community)
                .coImgLink(imgUrl)
                .build();
    }
}
