package com.contest.chaeso.domain.community.category.domain.repository;

import com.contest.chaeso.domain.community.category.domain.CommunityCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityCategoryRepository extends JpaRepository<CommunityCategory, Long> {
}
