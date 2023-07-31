package com.contest.chaeso.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SUCCESS(OK, "success"),
    NOT_FOUND_VEGAN_INFO(BAD_REQUEST, "비건 단계 정보가 올바르지 않습니다."),
    NOT_FOUND_COMMUNITY_CATEGORY(BAD_REQUEST, "커뮤니티 카테고리의 정보가 올바르지 않습니다."),
    NOT_FOUND_USER(BAD_REQUEST, "회원 정보가 올바르지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
