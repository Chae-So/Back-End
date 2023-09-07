package com.contest.chaeso.domain.community.img.domain.repository;

import com.contest.chaeso.domain.community.img.domain.CommunityImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityImgRepository extends JpaRepository<CommunityImg, Long>, CommunityImgQueryRepository {

    List<CommunityImg> findByCommunityId(Long communityId);
}
