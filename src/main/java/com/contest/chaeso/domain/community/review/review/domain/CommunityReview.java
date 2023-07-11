package com.contest.chaeso.domain.community.review.review.domain;

import com.contest.chaeso.domain.common.BaseTimeEntity;
import com.contest.chaeso.domain.community.community.domain.Community;
import com.contest.chaeso.domain.users.users.domain.Users;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;

@Entity
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityReview extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_reply_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "co_id")
    private Community community;

    @NotNull
    @Column(length = 30)
    private String writer;

    @NotNull
    @Column(columnDefinition = "text")
    private String contents;

    @NotNull
    private Integer score;

}
