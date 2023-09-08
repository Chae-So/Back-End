package com.contest.chaeso.domain.community.img.application;

import com.contest.chaeso.domain.community.community.domain.Community;
import com.contest.chaeso.domain.community.community.domain.repository.CommunityRepository;
import com.contest.chaeso.domain.community.img.api.dto.res.ResponseCommunityImgListDto;
import com.contest.chaeso.domain.community.img.domain.CommunityImg;
import com.contest.chaeso.domain.community.img.domain.repository.CommunityImgQueryRepository;
import com.contest.chaeso.domain.community.img.domain.repository.CommunityImgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityImgService {

    private final CommunityImgRepository communityImgRepository;
    private final CommunityImgQueryRepository communityImgQueryRepositoryImpl;

    @Transactional(readOnly = true)
    public List<ResponseCommunityImgListDto> communityImgList() {
        return communityImgRepository.findAll().stream().map(img -> ResponseCommunityImgListDto.communityImgList(img)).collect(Collectors.toList());
    }
}
