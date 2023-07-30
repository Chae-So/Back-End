package com.contest.chaeso.domain.community.community.api;

import com.contest.chaeso.domain.community.community.api.dto.res.ResponseCommunityListDto;
import com.contest.chaeso.domain.community.community.application.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/community")
public class CommunityController {

    private final CommunityService communityService;

    /**
     * 커뮤니티 게시글 리스트
     * @return communityList
     */
    @GetMapping
    public List<ResponseCommunityListDto> communityList(String sortOrder) {
        List<ResponseCommunityListDto> communityList = communityService.communityList(sortOrder);
        return communityList;
    }

    @PostMapping("/posting")
    public ResponseEntity postingCommunity() {
        return null;
    }

}
