package com.contest.chaeso.domain.community.bookmark.api;

import com.contest.chaeso.domain.community.bookmark.application.CommunityBookmarkService;
import com.contest.chaeso.domain.community.community.api.dto.res.ResponseCommunityListDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "CommunityBookmarkController")
@RequestMapping("/api/community-bookmark")
public class CommunityBookmarkController {

    private final CommunityBookmarkService communityBookmarkService;

    @ApiOperation(value = "커뮤니티 북마크 리스트", notes = "북마크 한 커뮤니티의 게시글을 출력합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    @GetMapping
    public ResponseEntity<List<ResponseCommunityListDto>> communityBookmarkList(@AuthenticationPrincipal UserDetails user, String orderType) {
        return ResponseEntity.ok(communityBookmarkService.getCommunityBookmarkList(user.getUsername(), orderType));
    }

    @ApiOperation(value = "북마크 추가", notes = "커뮤니티 게시글 한건을 북마크에 추가합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 400, message = "fail")
    })
    @PostMapping("/add-bookmark/{communityId}")
    public ResponseEntity addToBookmark(@PathVariable Long communityId, @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(communityBookmarkService.addToBookmark(communityId, user.getUsername()));
    }
}
