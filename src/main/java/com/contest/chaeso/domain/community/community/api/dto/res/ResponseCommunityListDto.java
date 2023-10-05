package com.contest.chaeso.domain.community.community.api.dto.res;

import com.contest.chaeso.domain.community.img.api.dto.res.ResponseCommunityImgDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCommunityListDto {

    private Long communityId;
    private String profilePicture;
    private String nickname;
    private Long likeCount;
    private Long reviewCount;
    private List<ResponseCommunityImgDto> imgList = new ArrayList<>();

    public ResponseCommunityListDto(Long communityId, String profilePicture, String nickname, Long likeCount, Long reviewCount) {
        this.communityId = communityId;
        this.profilePicture = profilePicture;
        this.nickname = nickname;
        this.likeCount = likeCount;
        this.reviewCount = reviewCount;
    }
}
