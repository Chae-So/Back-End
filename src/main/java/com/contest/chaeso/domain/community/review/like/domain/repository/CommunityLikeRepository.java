package com.contest.chaeso.domain.community.review.like.domain.repository;

import com.contest.chaeso.domain.community.like.domain.CommunityLike;
import com.contest.chaeso.domain.users.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CommunityLikeRepository extends JpaRepository<CommunityLike, Long> {
    CommunityLike findByUsers(Users users);

    Long countById(Long id);
}
