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
    NOT_FOUND_USER(BAD_REQUEST, "회원 정보가 올바르지 않습니다."),
    ALREADY_EXIST_EMAIL(BAD_REQUEST, "이미 존재하는 이메일입니다."),
    ALREADY_EXIST_NICKNAME(BAD_REQUEST, "이미 존재하는 닉네임입니다."),
    NOT_FOUND_COMMUNITY_POSTS(BAD_REQUEST, "해당 게시글이 존재하지 않습니다."),
    NOT_FOUND_LANGUAGE(BAD_REQUEST, "해당 언어가 존재하지 않습니다."),
    S3_FILE_UPLOAD(BAD_REQUEST, "S3 file upload에 실패하였습니다."),
    NOT_FOUND_RESTAURANT(BAD_REQUEST, "해당 레스토랑이 존재하지 않습니다."),
    NOT_FOUND_RESTAURANT_REVIEW(BAD_REQUEST, "해당 레스토랑의 리뷰가 존재하지 않습니다.");


    private final HttpStatus httpStatus;
    private final String message;
}
