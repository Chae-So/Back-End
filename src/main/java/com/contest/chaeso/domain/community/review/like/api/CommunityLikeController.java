package com.contest.chaeso.domain.community.review.like.api;

import com.contest.chaeso.domain.community.review.like.api.dto.req.RequestCommunityLikeDto;
import com.contest.chaeso.domain.community.review.like.application.CommunityLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/community-like")
public class CommunityLikeController {

    private final CommunityLikeService communityLikeService;

    /**
     * 커뮤니티 게시글 좋아요
     * @param communityLikeDto
     * @return ResponseEntity.ok(likeCount)
     */
    @PostMapping
    public ResponseEntity likeButton(@ModelAttribute RequestCommunityLikeDto communityLikeDto) {
        Long likeCount = communityLikeService.likeActionButton(communityLikeDto);
        return ResponseEntity.ok(likeCount);
    }


}
