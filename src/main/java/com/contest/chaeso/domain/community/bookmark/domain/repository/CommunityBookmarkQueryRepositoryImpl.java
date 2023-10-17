package com.contest.chaeso.domain.community.bookmark.domain.repository;

import com.contest.chaeso.domain.community.bookmark.domain.QCommunityBookmark;
import com.contest.chaeso.domain.community.community.api.dto.res.ResponseCommunityListDto;
import com.contest.chaeso.domain.community.img.api.dto.res.ResponseCommunityImgDto;
import com.contest.chaeso.domain.users.users.domain.Users;
import com.contest.chaeso.global.util.OrderByNull;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

import static com.contest.chaeso.domain.community.bookmark.domain.QCommunityBookmark.communityBookmark;
import static com.contest.chaeso.domain.community.community.domain.QCommunity.community;
import static com.contest.chaeso.domain.community.img.domain.QCommunityImg.communityImg;
import static com.contest.chaeso.domain.community.like.domain.QCommunityLike.communityLike;
import static com.contest.chaeso.domain.community.review.review.domain.QCommunityReview.communityReview;

@Repository
public class CommunityBookmarkQueryRepositoryImpl implements CommunityBookmarkQueryRepository {

    private final JPAQueryFactory query;

    public CommunityBookmarkQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<ResponseCommunityListDto> findCommunityBookmarkList(Users findUser, String orderType) {
        return query
                .select(Projections.constructor(
                                ResponseCommunityListDto.class,
                                community.id,
                                community.users.picture,
                                community.users.nickname,
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
                                ),
                                Projections.list(
                                        Projections.constructor(
                                                ResponseCommunityImgDto.class,
                                                community.id,
                                                communityImg.id,
                                                communityImg.coImgLink
                                        )
                                )

                        )
                )
                .from(community)
                .leftJoin(communityImg).on(communityImg.community.eq(community))
                .leftJoin(communityLike).on(communityLike.community.eq(community))
                .leftJoin(communityBookmark).on(communityBookmark.community.eq(community))
                .orderBy(communityListSortOrderCond(orderType))
                .orderBy(community.createdAt.desc())
                .where(communityBookmark.users.eq(findUser))
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
