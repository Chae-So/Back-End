package com.contest.chaeso.domain.users.users.api.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RequestUserSignUpDto {

    private String email;
    private String pw;
    private String nickname;
}
