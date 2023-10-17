package com.contest.chaeso.domain.community.bookmark.domain;

import com.contest.chaeso.domain.community.community.domain.Community;
import com.contest.chaeso.domain.users.users.domain.Users;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityBookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_bmark_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "co_id")
    private Community community;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    public static CommunityBookmark addCommunity(Community community, Users user) {
        return CommunityBookmark.builder()
                .community(community)
                .users(user)
                .build();
    }
}
