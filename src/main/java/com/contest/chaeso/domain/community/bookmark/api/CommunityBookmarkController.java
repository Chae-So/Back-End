package com.contest.chaeso.domain.community.bookmark.api;

import com.contest.chaeso.domain.community.bookmark.application.CommunityBookmarkService;
import com.contest.chaeso.domain.community.community.api.dto.res.ResponseCommunityListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/community-bookmark")
public class CommunityBookmarkController {

    private final CommunityBookmarkService communityBookmarkService;

    @GetMapping
    public ResponseEntity<List<ResponseCommunityListDto>> communityBookmarkList(@AuthenticationPrincipal UserDetails user, String orderType) {
        return ResponseEntity.ok(communityBookmarkService.getCommunityBookmarkList(user.getUsername(), orderType));
    }

    @PostMapping("/add-bookmark/{communityId}")
    public ResponseEntity addToBookmark(@PathVariable Long communityId, @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(communityBookmarkService.addToBookmark(communityId, user.getUsername()));
    }
}
