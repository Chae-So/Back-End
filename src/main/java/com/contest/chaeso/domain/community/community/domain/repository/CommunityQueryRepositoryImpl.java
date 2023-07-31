package com.contest.chaeso.domain.community.community.domain.repository;

import com.contest.chaeso.domain.community.community.api.dto.res.QResponseCommunityListDto;
import com.contest.chaeso.domain.community.community.api.dto.res.ResponseCommunityListDto;
import com.contest.chaeso.domain.community.review.review.api.dto.res.ResponseCommunityReviewListDto;
import com.contest.chaeso.global.util.OrderByNull;
import com.querydsl.core.types.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

import static com.contest.chaeso.domain.community.community.domain.QCommunity.community;
import static com.contest.chaeso.domain.community.img.domain.QCommunityImg.communityImg;
import static com.contest.chaeso.domain.community.like.domain.QCommunityLike.communityLike;
import static com.contest.chaeso.domain.community.review.review.domain.QCommunityReview.communityReview;

@Repository
@Slf4j
public class CommunityQueryRepositoryImpl implements CommunityQueryRepository{

    private final JPAQueryFactory query;

    public CommunityQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<ResponseCommunityListDto> findCommunityList(String sortOrder) {

        return query
                .select(new QResponseCommunityListDto(
                        community.id,
                        community.users.picture,
                        community.users.nickname,
                        communityImg.coImgLink,
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(communityLike.count())
                                        .from(communityLike)
                                        .where(communityLike.community.eq(community)),
                                "likeCount"

                        ),
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(communityReview.count())
                                        .from(communityReview)
                                        .where(communityReview.community.eq(community)),
                                "reviewCount"
                        )
                ))
                .from(community)
                .leftJoin(communityImg).on(communityImg.community.eq(community))
                .leftJoin(communityLike).on(communityLike.community.eq(community))
                .orderBy(communityListSortOrderCond(sortOrder))
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
