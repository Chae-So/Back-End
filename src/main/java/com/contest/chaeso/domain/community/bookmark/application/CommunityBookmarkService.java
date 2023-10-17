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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityBookmarkService {

    private final CommunityBookmarkRepository communityBookmarkRepository;
    private final CommunityRepository communityRepository;
    private final UsersRepository usersRepository;

    @Transactional(readOnly = true)
    public List<ResponseCommunityListDto> getCommunityBookmarkList(String email, String orderType) {
        Users findUser = usersRepository.findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        return communityBookmarkRepository.findCommunityBookmarkList(findUser, orderType);
    }

    @Transactional
    public boolean addToBookmark(Long communityId, String email) {
        Community findCommunity = communityRepository.findById(communityId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMUNITY_POSTS));
        Users findUser = usersRepository.findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        communityBookmarkRepository.findByCommunityAndUsers(findCommunity, findUser).ifPresentOrElse(
                alreadyCheck -> communityBookmarkRepository.delete(alreadyCheck),
                () -> communityBookmarkRepository.save(CommunityBookmark.addCommunity(findCommunity, findUser)));
        return communityBookmarkRepository.findByCommunityAndUsers(findCommunity, findUser).isPresent();
    }
}
