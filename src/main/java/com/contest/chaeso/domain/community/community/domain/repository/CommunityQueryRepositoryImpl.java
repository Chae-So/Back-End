package com.contest.chaeso.domain.community.community.domain.repository;

import com.contest.chaeso.domain.community.community.api.dto.res.ResponseCommunityListDto;
import com.contest.chaeso.global.util.OrderByNull;
import com.querydsl.core.types.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

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
    public List<ResponseCommunityListDto> findCommunityList(String sortOrder) {

        return query
                .select(Projections.constructor(
                        ResponseCommunityListDto.class,
                        community.id,
                        community.users.picture,
                        community.users.nickname,
                        communityImg.coImgLink,
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(communityLike.users.count())
                                        .from(communityLike),
                                        "likeCount"

                        ),
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(communityReview.users.count())
                                        .from(communityReview),
                                        "reviewCount"

                        ),
                        communityReview.users.picture,
                        communityReview.users.nickname,
                        communityReview.contents,
                        communityReview.score
                ))
                .from(community, communityLike, communityReview, communityImg)
                .join(communityLike.community, community)
                .join(communityReview.community, community)
                .join(communityImg.community, community)
                .orderBy(communityListSortOrderCond(sortOrder))
                .having()
                .fetch();
    }

    private OrderSpecifier<?> communityListSortOrderCond(String sortOrder) {
        Order order = Order.DESC;

        if (Objects.isNull(sortOrder)) {
            return new OrderSpecifier<>(order, communityLike.id);
        }

        if (sortOrder.equals("BEST")) {
            return new OrderSpecifier<>(order, communityLike.id);
        } else if (sortOrder.equals("RECENT")) {
            return new OrderSpecifier<>(order, community.createdAt);
        }

        return OrderByNull.getDefault();
    }
}
