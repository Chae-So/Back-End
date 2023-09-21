package com.contest.chaeso.domain.community.img.application;

import com.contest.chaeso.domain.community.img.api.dto.res.ResponseCommunityImgDto;
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

}
