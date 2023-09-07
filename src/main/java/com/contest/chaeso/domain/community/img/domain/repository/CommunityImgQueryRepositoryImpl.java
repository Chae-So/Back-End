package com.contest.chaeso.domain.community.img.domain.repository;

import com.contest.chaeso.domain.community.community.domain.QCommunity;
import com.contest.chaeso.domain.community.img.api.dto.res.ResponseCommunityImgListDto;
import com.contest.chaeso.domain.community.img.domain.QCommunityImg;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.contest.chaeso.domain.community.community.domain.QCommunity.community;
import static com.contest.chaeso.domain.community.img.domain.QCommunityImg.communityImg;

@Repository
public class CommunityImgQueryRepositoryImpl implements CommunityImgQueryRepository {

    private final JPAQueryFactory query;

    public CommunityImgQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

}
