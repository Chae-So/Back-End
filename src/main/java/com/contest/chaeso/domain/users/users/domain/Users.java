package com.contest.chaeso.domain.users.users.domain;


import com.contest.chaeso.domain.common.BaseTimeEntity;
import com.contest.chaeso.domain.users.language.domain.LanguageInfo;
import com.contest.chaeso.domain.users.vegan.domain.VeganInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "vegan_id")
    private VeganInfo veganInfo;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "lang_id")
    private LanguageInfo languageInfo;

    @NotNull
    @Column(length = 50)
    private String email;

    @NotNull
    @Column(length = 100)
    private String pw;

    @NotNull
    @Column(length = 30)
    private String nickname;

    @NotNull
    @Column(length = 50)
    private String name;

    @Column(length = 512)
    private String picture;

    @NotNull
    @Column(length = 40)
    private String provider;



    /**
     * oneToMany
     *
     * restaurant bookmark
     *
     * restaurant review
     */

}
