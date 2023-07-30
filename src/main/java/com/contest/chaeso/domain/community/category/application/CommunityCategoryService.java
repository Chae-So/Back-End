package com.contest.chaeso.domain.community.category.application;


import com.contest.chaeso.domain.community.category.api.dto.res.ResponseCommunityCategoryListDto;
import com.contest.chaeso.domain.community.category.domain.CommunityCategory;
import com.contest.chaeso.domain.community.category.domain.CommunityCategoryType;
import com.contest.chaeso.domain.community.category.domain.repository.CommunityCategoryRepository;
import com.contest.chaeso.domain.community.community.api.dto.res.ResponseCommunityListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityCategoryService {

    private final CommunityCategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Map<Long, String> communityCategoryList() {

        Map<Long, String> contents = new HashMap<>();
        List<ResponseCommunityCategoryListDto> categoryList = categoryRepository.findAll().stream().map(category -> ResponseCommunityCategoryListDto.communityList(category)).collect(Collectors.toList());
        for (ResponseCommunityCategoryListDto responseCommunityCategoryListDto : categoryList) {
            contents.put(responseCommunityCategoryListDto.getCategoryId(), responseCommunityCategoryListDto.getCategoryName());
        }

        return contents;
    }

}
