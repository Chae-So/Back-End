package com.contest.chaeso.domain.community.category.api;

import com.contest.chaeso.domain.community.category.api.dto.res.ResponseCommunityCategoryListDto;
import com.contest.chaeso.domain.community.category.application.CommunityCategoryService;
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
@RequestMapping("/community-category")
public class CommunityCategoryController {

    private final CommunityCategoryService categoryService;

    /**
     * 커뮤니티 카테고리 리스트
     * @return ResponseCommunityCategoryListDto
     */
    @GetMapping
    public Map<Long, String> communityCategoryList() {
        return categoryService.communityCategoryList();
    }
}
