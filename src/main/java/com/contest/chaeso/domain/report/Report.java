package com.contest.chaeso.domain.report;

import com.contest.chaeso.domain.community.community.domain.Community;
import com.contest.chaeso.domain.community.review.review.domain.CommunityReview;
import com.contest.chaeso.domain.users.users.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_id")
    private Community community;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_reply_id")
    private CommunityReview communityReview;

    public static Report reportFunction(Users users, Community community, CommunityReview review) {
        return Report.builder()
                .users(users)
                .community(community)
                .communityReview(review)
                .build();
    }



}
