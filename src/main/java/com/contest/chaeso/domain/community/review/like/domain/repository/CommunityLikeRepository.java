package com.contest.chaeso.domain.community.review.like.domain.repository;

import com.contest.chaeso.domain.community.like.domain.CommunityLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityLikeRepository extends JpaRepository<CommunityLike, Long> {
}
