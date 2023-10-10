package com.contest.chaeso.domain.community.review.review.api;

import com.contest.chaeso.domain.community.review.review.api.dto.req.RequestCommunityReviewWriteFormDto;
import com.contest.chaeso.domain.community.review.review.application.CommunityReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/community-review")
public class CommunityReviewController {

    private final CommunityReviewService communityReviewService;

    /**
     * 커뮤니티 리뷰 한건 미리보기
     * @param communityId
     * @return ResponseEntity
     */
    @GetMapping("/{communityId}")
    public ResponseEntity reviewOne(@PathVariable(value = "communityId") Long communityId) {
        return ResponseEntity.ok(communityReviewService.reviewOne(communityId));
    }

    /**
     * 커뮤니티 리뷰 전체 리스트
     * @param communityId
     * @return ResponseEntity
     */
    @GetMapping("review/{communityId}")
    public ResponseEntity reviewList(@PathVariable(value = "communityId") Long communityId) {
        return ResponseEntity.ok(communityReviewService.reviewList(communityId));
    }

    /**
     * 리뷰 작성
     * @param writeFormDto
     * @return ResponseEntity
     */
    @PostMapping("/write")
    public ResponseEntity writeReview(@ModelAttribute RequestCommunityReviewWriteFormDto writeFormDto) {
        return ResponseEntity.ok(communityReviewService.writeActionReview(writeFormDto));
    }



}
