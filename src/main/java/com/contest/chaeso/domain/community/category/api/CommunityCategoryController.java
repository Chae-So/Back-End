package com.contest.chaeso.domain.community.category.api;

import com.contest.chaeso.domain.community.category.api.dto.res.ResponseCommunityCategoryListDto;
import com.contest.chaeso.domain.community.category.application.CommunityCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(value = "CommunityCategoryController")
@RequestMapping("/community-category")
public class CommunityCategoryController {

    private final CommunityCategoryService categoryService;

    @ApiOperation(value = "커뮤니티 카테고리", notes = "커뮤니티 카테고리 리스트를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    /**
     * 커뮤니티 카테고리 리스트
     * @return ResponseCommunityCategoryListDto
     */
    @GetMapping
    public Map<Long, String> communityCategoryList() {
        return categoryService.communityCategoryList();
    }
}
