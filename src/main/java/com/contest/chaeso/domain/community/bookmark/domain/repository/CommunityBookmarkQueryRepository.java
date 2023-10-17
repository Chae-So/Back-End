package com.contest.chaeso.domain.community.bookmark.domain.repository;

import com.contest.chaeso.domain.community.community.api.dto.res.ResponseCommunityListDto;
import com.contest.chaeso.domain.users.users.domain.Users;

import java.util.List;

public interface CommunityBookmarkQueryRepository {

    List<ResponseCommunityListDto> findCommunityBookmarkList(Users findUser, String orderType);
}
