package com.contest.chaeso.domain.community.review.review.domain.repository;

import com.contest.chaeso.domain.community.review.review.api.dto.res.QResponseCommunityReviewListDto;
import com.contest.chaeso.domain.community.review.review.api.dto.res.ResponseCommunityReviewListDto;
import com.contest.chaeso.domain.community.review.review.domain.QCommunityReview;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.contest.chaeso.domain.community.review.review.domain.QCommunityReview.communityReview;

@Repository
public class CommunityReviewQueryRepositoryImpl implements CommunityReviewQueryRepository{

    private final JPAQueryFactory query;

    public CommunityReviewQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<ResponseCommunityReviewListDto> findCommunityReviewListByCommunityId(Long communityId) {
        return query
                .select(new QResponseCommunityReviewListDto(
                        communityReview.users.userId,
                        communityReview.users.picture,
                        communityReview.users.nickname,
                        communityReview.score
                ))
                .from(communityReview)
                .where(communityReview.community.id.eq(communityId))
                .orderBy(communityReview.score.desc(), communityReview.createdAt.asc())
                .fetch();
    }

    @Override
    public ResponseCommunityReviewListDto findCommunityReviewOneByCommunityId(Long communityId) {
        return query
                .select(new QResponseCommunityReviewListDto(
                        communityReview.users.userId,
                        communityReview.users.picture,
                        communityReview.users.nickname,
                        communityReview.score
                ))
                .from(communityReview)
                .where(communityReview.community.id.eq(communityId))
                .orderBy(communityReview.score.desc(), communityReview.createdAt.asc())
                .limit(1)
                .fetchOne();
    }
}
