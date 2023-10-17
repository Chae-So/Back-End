package com.contest.chaeso.domain.community.bookmark.application;

import com.contest.chaeso.domain.community.bookmark.domain.CommunityBookmark;
import com.contest.chaeso.domain.community.bookmark.domain.repository.CommunityBookmarkRepository;
import com.contest.chaeso.domain.community.community.api.dto.res.ResponseCommunityListDto;
import com.contest.chaeso.domain.community.community.domain.Community;
import com.contest.chaeso.domain.community.community.domain.repository.CommunityRepository;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class CommunityBookmarkServiceTest {

    @Autowired
    private CommunityBookmarkRepository communityBookmarkRepository;
    @Autowired
    private CommunityRepository communityRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Test
    @DisplayName("커뮤니티 북마크 피드 리스트")
    public void communityBookmarkListTest() throws Exception {
        Users findUser = usersRepository.findByEmail("skarms92@naver.com").orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        Community findCommunity = communityRepository.findById(714L).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMUNITY_POSTS));
        communityBookmarkRepository.findByCommunityAndUsers(findCommunity, findUser).ifPresentOrElse(
                alreadyCheck -> communityBookmarkRepository.delete(alreadyCheck),
                () -> communityBookmarkRepository.save(CommunityBookmark.addCommunity(findCommunity, findUser)));
        assertThat(communityBookmarkRepository.findByCommunityAndUsers(findCommunity, findUser).isPresent()).isTrue();
    }

}