package com.contest.chaeso.domain.community.category.application;


import com.contest.chaeso.domain.community.category.api.dto.res.ResponseCommunityCategoryListDto;
import com.contest.chaeso.domain.community.category.domain.repository.CommunityCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        return categoryRepository.findAll().stream()
                .map(ResponseCommunityCategoryListDto::communityList)
                .collect(Collectors.toMap(ResponseCommunityCategoryListDto::getCategoryId, ResponseCommunityCategoryListDto::getCategoryName));
    }

}