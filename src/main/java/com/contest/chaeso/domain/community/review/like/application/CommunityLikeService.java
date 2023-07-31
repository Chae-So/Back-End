package com.contest.chaeso.domain.community.review.like.application;

import com.contest.chaeso.domain.community.community.domain.Community;
import com.contest.chaeso.domain.community.community.domain.repository.CommunityRepository;
import com.contest.chaeso.domain.community.like.domain.CommunityLike;
import com.contest.chaeso.domain.community.review.like.api.dto.req.RequestCommunityLikeDto;
import com.contest.chaeso.domain.community.review.like.domain.repository.CommunityLikeRepository;
import com.contest.chaeso.domain.users.users.domain.Users;
import com.contest.chaeso.domain.users.users.domain.repository.UsersRepository;
import com.contest.chaeso.global.exception.CustomException;
import com.contest.chaeso.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityLikeService {

    private final CommunityLikeRepository communityLikeRepository;
    private final CommunityRepository communityRepository;
    private final UsersRepository usersRepository;

    @Transactional
    public Long likeActionButton(RequestCommunityLikeDto communityLikeDto) {
        Community community = communityRepository.findById(communityLikeDto.getCommunityId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMUNITY_POSTS));
        Users users = usersRepository.findUsersByNickname(communityLikeDto.getNickname()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        CommunityLike myCommunityLike = communityLikeRepository.findByUsers(users);
        Long likeCount = 0L;
        if (Objects.isNull(myCommunityLike)) {
            CommunityLike savedCommunityLike = communityLikeRepository.save(communityLikeDto.toEntity(community, users));
            likeCount = communityLikeRepository.countById(savedCommunityLike.getId());
        } else {
            communityLikeRepository.delete(myCommunityLike);
        }
        return likeCount;
    }
}
