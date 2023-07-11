package com.contest.chaeso.domain.community.review.like.domain;

import com.contest.chaeso.domain.community.review.review.domain.CommunityReview;
import com.contest.chaeso.domain.users.users.domain.Users;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityReviewLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_reply_like_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "co_reply_id")
    private CommunityReview communityReview;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

}
