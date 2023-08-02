package com.contest.chaeso.domain.community.review.review.domain.repository;

import com.contest.chaeso.domain.community.review.review.domain.CommunityReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityReviewRepository extends JpaRepository<CommunityReview, Long>, CommunityReviewQueryRepository {


}
