package com.contest.chaeso.global.resolver;

import com.contest.chaeso.global.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserInfoFromHeaderArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtService jwtService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserInfoFromHeaderDto.class);
    }

    /**
     * 헤더에서 jwt accessToken 추출 -> email 추출
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String accessToken = jwtService.extractAccessToken((HttpServletRequest) webRequest.getNativeRequest()).orElseThrow(() -> new IllegalArgumentException("accesstoken이 존재하지 않습니다."));

        String email = jwtService.extractEmail(accessToken).orElseThrow(() -> new IllegalArgumentException("email 존재하지 않습니다."));

        log.info("========== UserInfoFromHeaderArgumentResolver ==========");
        log.info("========== Get email : " + email + " ==========");

        return new UserInfoFromHeaderDto(email);
    }
}
