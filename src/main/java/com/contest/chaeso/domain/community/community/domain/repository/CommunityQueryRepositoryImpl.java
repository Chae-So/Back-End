package com.contest.chaeso.domain.community.community.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CommunityQueryRepositoryImpl implements CommunityQueryRepository{

    private final JPAQueryFactory query;

    public CommunityQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }
}
