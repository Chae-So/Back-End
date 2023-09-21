package com.contest.chaeso.domain.community.community.application;

import com.contest.chaeso.domain.community.community.api.dto.req.RequestCommunityFormDto;
import com.contest.chaeso.domain.community.community.api.dto.res.ResponseCommunityListDto;
import com.contest.chaeso.domain.community.community.domain.Community;
import com.contest.chaeso.domain.community.community.domain.repository.CommunityQueryRepository;
import com.contest.chaeso.domain.community.community.domain.repository.CommunityRepository;
import com.contest.chaeso.domain.community.img.api.dto.req.RequestCommunityImgDto;
import com.contest.chaeso.domain.community.img.api.dto.res.ResponseCommunityImgDto;
import com.contest.chaeso.domain.community.img.domain.CommunityImg;
import com.contest.chaeso.domain.community.img.domain.repository.CommunityImgRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final CommunityQueryRepository communityQueryRepositoryImpl;
    private final CommunityImgRepository communityImgRepository;

    @Transactional(readOnly = true)
    public List<ResponseCommunityListDto> communityList(String sortOrder) {
        List<ResponseCommunityListDto> communityList = communityQueryRepositoryImpl.findCommunityList(sortOrder);
        return communityList;
    }

    @Transactional
    public Long postingCommunity(RequestCommunityFormDto communityFormDto) {
        Community savedCommunityPost = communityRepository.save(communityFormDto.toEntity());
        if (!Objects.isNull(communityFormDto.getImgUrl())) {
            List<String> imgUrl = communityFormDto.getImgUrl();
            for (String img : imgUrl) {
                communityImgRepository.save(new RequestCommunityImgDto(savedCommunityPost, img).toEntity());
            }
        }
        return savedCommunityPost.getId();
    }
}
