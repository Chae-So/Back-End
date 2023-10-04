package com.contest.chaeso.domain.users.language.api.dto.res;

import com.contest.chaeso.domain.users.users.domain.Role;
import com.contest.chaeso.domain.users.users.domain.SocialType;
import com.contest.chaeso.domain.users.users.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ResponseOAuthUserInfoDto {

    private String socialId;
    private SocialType socialType;
    private String email;
    private String nickname;
    private Role userRole;
    private String accessToken;
    private String refreshToken;

    public static ResponseOAuthUserInfoDto getUserInfo(Optional<Users> user) {
        return ResponseOAuthUserInfoDto.builder()
                .email(user.get().getEmail())
                .nickname(user.get().getNickname())
                .userRole(user.get().getRole())
                .accessToken(user.get().getAccessToken())
                .refreshToken(user.get().getRefreshToken())
                .socialType(user.get().getSocialType())
                .socialId(user.get().getSocialId())
                .build();
    }
}
