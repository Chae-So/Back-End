package com.contest.chaeso.domain.community.review.review.application;

import com.contest.chaeso.domain.community.community.domain.Community;
import com.contest.chaeso.domain.community.community.domain.repository.CommunityRepository;
import com.contest.chaeso.domain.community.review.review.api.dto.req.RequestCommunityReviewWriteFormDto;
import com.contest.chaeso.domain.community.review.review.api.dto.res.ResponseCommunityReviewListDto;
import com.contest.chaeso.domain.community.review.review.domain.CommunityReview;
import com.contest.chaeso.domain.community.review.review.domain.repository.CommunityReviewQueryRepository;
import com.contest.chaeso.domain.community.review.review.domain.repository.CommunityReviewRepository;
import com.contest.chaeso.domain.users.users.domain.Users;
import com.contest.chaeso.domain.users.users.domain.repository.UsersRepository;
import com.contest.chaeso.global.exception.CustomException;
import com.contest.chaeso.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityReviewService {

    private final UsersRepository usersRepository;
    private final CommunityRepository communityRepository;
    private final CommunityReviewRepository communityReviewRepository;
    private final CommunityReviewQueryRepository communityReviewQueryRepositoryImpl;

    @Transactional(readOnly = true)
    public List<ResponseCommunityReviewListDto> reviewList(Long communityId) {
        return communityReviewQueryRepositoryImpl.findCommunityReviewListByCommunityId(communityId);
    }

    @Transactional(readOnly = true)
    public ResponseCommunityReviewListDto reviewOne(Long communityId) {
        return communityReviewQueryRepositoryImpl.findCommunityReviewOneByCommunityId(communityId);
    }

    @Transactional
    public Long writeActionReview(RequestCommunityReviewWriteFormDto writeFormDto) {
        Community findCommunity = communityRepository.findById(writeFormDto.getCommunityId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMUNITY_POSTS));
        Users findUser = usersRepository.findUsersByNickname(writeFormDto.getNickname()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        return communityReviewRepository.save(writeFormDto.toEntity(findUser, findCommunity)).getId();
    }
}
