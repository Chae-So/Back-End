package com.contest.chaeso.domain.users.users.api.dto.req;

import com.contest.chaeso.domain.users.language.domain.Language;
import com.contest.chaeso.domain.users.vegan.domain.VeganLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RequestUserSignUpDto {

    private String email;
    private String pw;
    private String nickname;
    private Long languageId;
    private Long veganId;
}
