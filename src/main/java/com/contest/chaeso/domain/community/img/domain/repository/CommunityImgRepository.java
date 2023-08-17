package com.contest.chaeso.domain.community.img.domain.repository;

import com.contest.chaeso.domain.community.img.api.dto.res.ResponseImgListDto;
import com.contest.chaeso.domain.community.img.domain.CommunityImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityImgRepository extends JpaRepository<CommunityImg, Long> {
    List<ResponseImgListDto> findByCommunityId(Long communityId);
}
