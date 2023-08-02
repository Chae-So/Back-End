package com.contest.chaeso.domain.community.community.domain;

import com.contest.chaeso.domain.common.BaseTimeEntity;
import com.contest.chaeso.domain.community.category.domain.CommunityCategory;
import com.contest.chaeso.domain.users.users.domain.Users;
import com.contest.chaeso.domain.users.vegan.domain.VeganInfo;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "co_cate_id")
    private CommunityCategory communityCategory;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "vegan_id")
    private VeganInfo veganInfo;

    @NotNull
    @Column(length = 30)
    private String writer;

    @NotNull
    @Column(columnDefinition = "text")
    private String contents;

    @NotNull
    @Column(length = 200)
    private String location;


}
