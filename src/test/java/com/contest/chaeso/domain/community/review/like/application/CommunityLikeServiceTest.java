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
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class CommunityLikeServiceTest {

    @Autowired
    CommunityLikeRepository communityLikeRepository;
    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    UsersRepository usersRepository;

    @Test
    @DisplayName("커뮤니티 게시글 좋아요")
    void likeButtonAction() {
        //given
        RequestCommunityLikeDto communityLikeDto = new RequestCommunityLikeDto("wolveshowl", 701L);
        Community community = communityRepository.findById(communityLikeDto.getCommunityId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMUNITY_POSTS));
        Users users = usersRepository.findUsersByNickname(communityLikeDto.getNickname()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        CommunityLike myCommunityLike = communityLikeRepository.findByUsers(users);
        //when
        Long likeCount = 0L;
        if (Objects.isNull(myCommunityLike)) {
            CommunityLike savedCommunityLike = communityLikeRepository.save(communityLikeDto.toEntity(community, users));
            likeCount = communityLikeRepository.countById(savedCommunityLike.getId());
        } else {
            communityLikeRepository.delete(myCommunityLike);
        }
        //then
        log.info("likeCount={}", likeCount);
        assertThat(likeCount).isNotZero();
    }
}