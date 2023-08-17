package com.contest.chaeso.domain.community.img.api.dto.res;

import com.contest.chaeso.domain.community.community.domain.Community;
import com.contest.chaeso.domain.community.img.domain.CommunityImg;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseImgListDto {

    private Long coImgId;
    private String imgUrl;

    @QueryProjection
    public ResponseImgListDto(Long coImgId, String imgUrl) {
        this.coImgId = coImgId;
        this.imgUrl = imgUrl;
    }
}
