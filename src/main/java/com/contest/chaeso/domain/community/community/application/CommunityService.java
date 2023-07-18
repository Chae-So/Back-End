package com.contest.chaeso.domain.community.community.application;

import com.contest.chaeso.domain.community.community.api.dto.res.ResponseCommunityListDto;
import com.contest.chaeso.domain.community.community.domain.repository.CommunityQueryRepository;
import com.contest.chaeso.domain.community.community.domain.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final CommunityQueryRepository communityQueryRepositoryImpl;

    @Transactional(readOnly = true)
    public List<ResponseCommunityListDto> communityList() {
        List<ResponseCommunityListDto> communityList = communityQueryRepositoryImpl.findCommunityList();
        return communityList;
    }
}
