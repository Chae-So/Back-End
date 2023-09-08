package com.contest.chaeso.domain.users.users.domain;


import com.contest.chaeso.domain.common.BaseTimeEntity;
import com.contest.chaeso.domain.users.language.domain.LanguageInfo;
import com.contest.chaeso.domain.users.vegan.domain.VeganInfo;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
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

    private String email;

    @Column(length = 100)
    private String pw;

    @Column(length = 30)
    private String nickname;

    @Column(length = 512)
    private String picture;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SocialType socialType; //APPLE, GOOGLE, KAKAO

    private String socialId; //소셜 타입 식별자 값(일반 로그인일시 null)

    private String refreshToken; //리프레시 토큰

    //유저 권한 설정 메서드
    public void authorizeUser() {
        this.role = Role.USER;
    }

    //비밀번호 암호화 메서드
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.pw = passwordEncoder.encode(this.pw);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }


    /**
     * oneToMany
     *
     * restaurant bookmark
     *
     * restaurant review
     */

}
