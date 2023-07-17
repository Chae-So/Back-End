package com.contest.chaeso.domain.community.community.domain.repository;

import com.contest.chaeso.domain.community.community.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {
}
