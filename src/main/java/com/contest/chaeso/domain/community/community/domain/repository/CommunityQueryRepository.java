package com.contest.chaeso.domain.community.community.domain.repository;

import com.contest.chaeso.domain.community.community.api.dto.res.ResponseCommunityListDto;

import java.util.List;

public interface CommunityQueryRepository {
    List<ResponseCommunityListDto> findCommunityList();
}
