package com.contest.chaeso.global.oauth2.userinfo;

import com.contest.chaeso.domain.users.language.api.dto.res.ResponseOAuthUserInfoDto;
import com.contest.chaeso.domain.users.users.domain.Users;
import com.contest.chaeso.global.join.api.dto.req.RequestOAuthUserInfoDto;

public interface OAuthLogin {

    String getAccessToken(String code);

    ResponseOAuthUserInfoDto getUserInfo(String code);

    Users userSave(RequestOAuthUserInfoDto userInfoDto);


}
