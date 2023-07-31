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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CommunityReviewServiceTest {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    CommunityReviewRepository communityReviewRepository;
    @Autowired
    CommunityReviewQueryRepository communityReviewQueryRepositoryImpl;

    CommunityReview addReview;

    @BeforeEach
    void addReview() {
        RequestCommunityReviewWriteFormDto writeFormDto = new RequestCommunityReviewWriteFormDto(701L, "wolveshowl", "테스트 댓글");
        Users findUser = usersRepository.findUsersByNickname(writeFormDto.getNickname()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        Community findCommunity = communityRepository.findById(writeFormDto.getCommunityId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMUNITY_POSTS));
        addReview = communityReviewRepository.save(writeFormDto.toEntity(findUser, findCommunity));
    }

    @Test
    @DisplayName("커뮤니티 게시글 한건의 리뷰 전체를 출력")
    void reviewList() {
        assertThat(addReview.getWriter()).isEqualTo("wolveshowl");
    }


    @Test
    @DisplayName("커뮤니티 피드의 리뷰 미리보기")
    void reviewOne() {
        ResponseCommunityReviewListDto findReview = communityReviewQueryRepositoryImpl.findCommunityReviewOneByCommunityId(701L);
        assertThat(findReview).isNotNull();
    }
}