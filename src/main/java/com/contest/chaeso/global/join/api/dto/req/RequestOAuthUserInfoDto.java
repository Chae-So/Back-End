package com.contest.chaeso.global.join.api.dto.req;

import com.contest.chaeso.domain.users.users.domain.Role;
import com.contest.chaeso.domain.users.users.domain.SocialType;
import com.contest.chaeso.domain.users.users.domain.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class RequestOAuthUserInfoDto {

    private SocialType socialType;
    private String socialId;
    private String email;
    private String nickname;
    private String accessToken;
    private String refreshToken;
    public RequestOAuthUserInfoDto(String socialId, SocialType socialType, String email, String nickname, String accessToken, String refreshToken){
        this.socialId = socialId;
        this.socialType = socialType;
        this.email = email;
        this.nickname = nickname;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public Users toEntity(RequestOAuthUserInfoDto userInfo){
        return Users.builder()
                .email(userInfo.email)
                .pw(String.valueOf(UUID.randomUUID()))
                .nickname(userInfo.nickname)
                .role(Role.USER)
                .accessToken(userInfo.accessToken)
                .refreshToken(userInfo.refreshToken)
                .socialType(userInfo.socialType)
                .socialId(userInfo.socialId)
                .build();
    }
}
