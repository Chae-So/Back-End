package com.contest.chaeso.domain.community.community.api.dto.res;

import com.contest.chaeso.domain.community.img.domain.CommunityImg;
import com.contest.chaeso.domain.community.like.domain.CommunityLike;
import com.contest.chaeso.domain.community.review.review.domain.CommunityReview;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseCommunityListDto {

//    private Long communityId;
//    private String picture;
//    private String nickname;
//    private String communityImg;
//    private Long likeCount;
//    private Long reviewCount;
//    private String reviewerPicture;
//    private String reviewerNickname;
//    private String reviewContent;
//    private Integer reviewScore;

    private Long communityId;
    private String profilePicture;
    private String nickname;
    private String communityImg;
    private Long likeCount;
    private Long reviewCount;

}
