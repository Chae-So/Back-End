package com.contest.chaeso.domain.community.community.api;

import com.contest.chaeso.domain.community.community.api.dto.req.RequestCommunityFormDto;
import com.contest.chaeso.domain.community.community.api.dto.res.ResponseCommunityListDto;
import com.contest.chaeso.domain.community.community.application.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/community")
public class CommunityController {

    private final CommunityService communityService;

    /**
     * 커뮤니티 게시글 리스트
     *
     * @return communityList
     */
    @GetMapping
    public List<ResponseCommunityListDto> communityList(String sortOrder) {
        return communityService.communityList(sortOrder);
    }

    /**
     * 커뮤니티 게시글 등록
     * @param communityFormDto
     * @return ResponseEntity
     */
    @PostMapping("/posting")
    public ResponseEntity postingCommunity(@ModelAttribute RequestCommunityFormDto communityFormDto) {
        Long savedId = communityService.postingCommunity(communityFormDto);
        return ResponseEntity.ok(savedId);
    }

}
