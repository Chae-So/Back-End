package com.contest.chaeso.domain.community.community.application;

import com.contest.chaeso.domain.community.community.api.dto.req.RequestCommunityFormDto;
import com.contest.chaeso.domain.community.community.api.dto.res.ResponseCommunityListDto;
import com.contest.chaeso.domain.community.community.domain.Community;
import com.contest.chaeso.domain.community.community.domain.repository.CommunityQueryRepository;
import com.contest.chaeso.domain.community.community.domain.repository.CommunityRepository;
import com.contest.chaeso.domain.community.img.api.dto.res.ResponseCommunityImgDto;
import com.contest.chaeso.domain.community.img.domain.CommunityImg;
import com.contest.chaeso.domain.community.img.domain.repository.CommunityImgRepository;
import com.contest.chaeso.domain.community.review.review.api.dto.res.ResponseCommunityReviewListDto;
import com.contest.chaeso.domain.community.review.review.domain.repository.CommunityReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    private final CommunityReviewRepository communityReviewRepository;

    @Transactional(readOnly = true)
    public List<ResponseCommunityListDto> communityList(String sortOrder) {
        List<ResponseCommunityListDto> communityList = communityQueryRepositoryImpl.findCommunityList(sortOrder);
        return communityList;
    }

    @Transactional
    public Long postingCommunity(RequestCommunityFormDto communityFormDto) {
        Community savedCommunityPost = communityRepository.save(communityFormDto.toEntity());
        if (!Objects.isNull(communityFormDto.getImgUrl())) {
            communityImgRepository.save(new ResponseCommunityImgDto(savedCommunityPost, communityFormDto.getImgUrl()).toEntity());
        }
        return savedCommunityPost.getId();
    }
}
