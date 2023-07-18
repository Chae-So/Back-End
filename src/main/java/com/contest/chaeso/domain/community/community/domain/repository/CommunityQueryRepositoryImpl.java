package com.contest.chaeso.domain.community.community.domain.repository;

import com.contest.chaeso.domain.community.community.api.dto.res.ResponseCommunityListDto;
import com.contest.chaeso.domain.community.community.domain.QCommunity;
import com.contest.chaeso.domain.community.img.domain.QCommunityImg;
import com.contest.chaeso.domain.community.like.domain.QCommunityLike;
import com.contest.chaeso.domain.community.review.review.domain.QCommunityReview;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.contest.chaeso.domain.community.community.domain.QCommunity.community;
import static com.contest.chaeso.domain.community.img.domain.QCommunityImg.communityImg;
import static com.contest.chaeso.domain.community.like.domain.QCommunityLike.communityLike;
import static com.contest.chaeso.domain.community.review.review.domain.QCommunityReview.communityReview;

@Repository
public class CommunityQueryRepositoryImpl implements CommunityQueryRepository{

    private final JPAQueryFactory query;

    public CommunityQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<ResponseCommunityListDto> findCommunityList() {

        return query
                .select(Projections.constructor(
                        ResponseCommunityListDto.class,
                        community.id,
                        community.users.userId,
                        communityImg,
                        communityLike,
                        communityReview
                ))
                .from(community, communityLike, communityReview, communityImg)
                .join(communityLike.community, community)
                .join(communityReview.community, community)
                .join(communityImg.community, community)
                .fetch();
    }
}
