package com.contest.chaeso.domain.community.community.api;

import com.contest.chaeso.domain.community.community.api.dto.req.RequestCommunityFormDto;
import com.contest.chaeso.domain.community.community.api.dto.res.ResponseCommunityListDto;
import com.contest.chaeso.domain.community.community.application.CommunityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(value = "CommunityController")
@RequestMapping("/community")
public class CommunityController {

    private final CommunityService communityService;


    /**
     * 커뮤니티 게시글 리스트
     *
     * @return communityList
     */
    @ApiOperation(value = "커뮤니티 리스트", notes = "커뮤니티의 피드 리스트를 전체 출력합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    @GetMapping
    public List<ResponseCommunityListDto> communityList(String sortOrder) {
        return communityService.communityList(sortOrder);
    }

    /**
     * 커뮤니티 게시글 등록
     * @param communityFormDto
     * @return ResponseEntity
     */
    @ApiOperation(value = "커뮤니티 게시글 등록", notes = "커뮤니티에 게시글을 한 건 작성합니다,")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    @PostMapping("/posting")
    public ResponseEntity postingCommunity(@ModelAttribute RequestCommunityFormDto communityFormDto) {
        Long savedId = communityService.postingCommunity(communityFormDto);
        return ResponseEntity.ok(savedId);
    }

}
