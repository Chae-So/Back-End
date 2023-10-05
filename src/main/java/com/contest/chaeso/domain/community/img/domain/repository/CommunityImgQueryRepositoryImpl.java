package com.contest.chaeso.domain.community.img.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CommunityImgQueryRepositoryImpl implements CommunityImgQueryRepository {

    private final JPAQueryFactory query;

    public CommunityImgQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

}
