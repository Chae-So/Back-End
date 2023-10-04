package com.contest.chaeso.domain.users.users.application;

import com.contest.chaeso.domain.users.language.api.dto.res.ResponseOAuthUserInfoDto;
import com.contest.chaeso.domain.users.language.domain.LanguageInfo;
import com.contest.chaeso.domain.users.language.domain.repository.LanguageRepository;
import com.contest.chaeso.domain.users.users.api.dto.req.RequestUserSignUpDto;
import com.contest.chaeso.domain.users.users.domain.Role;
import com.contest.chaeso.domain.users.users.domain.Users;
import com.contest.chaeso.domain.users.users.domain.repository.UsersRepository;
import com.contest.chaeso.domain.users.vegan.domain.VeganInfo;
import com.contest.chaeso.domain.users.vegan.domain.repository.VeganInfoRepository;
import com.contest.chaeso.global.exception.CustomException;
import com.contest.chaeso.global.exception.ErrorCode;
import com.contest.chaeso.global.oauth2.userinfo.GoogleSocialLogin;
import com.contest.chaeso.global.oauth2.userinfo.KakaoSocialLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final LanguageRepository languageRepository;
    private final VeganInfoRepository veganInfoRepository;
    private final GoogleSocialLogin googleSocialLogin;
    private final KakaoSocialLogin kakaoSocialLogin;

    @Transactional
    public void signUp(RequestUserSignUpDto userSignUpDto) throws Exception {

        if (usersRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_EXIST_EMAIL);
        }

        if (usersRepository.findUsersByNickname(userSignUpDto.getNickname()).isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_EXIST_NICKNAME);
        }

        LanguageInfo findLanguage = languageRepository.findById(userSignUpDto.getLanguageId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_LANGUAGE));
        VeganInfo findVegan = veganInfoRepository.findById(userSignUpDto.getVeganId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_VEGAN_INFO));

        Users user = Users.builder()
                .email(userSignUpDto.getEmail())
                .pw(userSignUpDto.getPw())
                .nickname(userSignUpDto.getNickname())
                .picture(userSignUpDto.getPicture())
                .languageInfo(findLanguage)
                .veganInfo(findVegan)
                .role(Role.USER)
                .build();

        user.passwordEncode(passwordEncoder);
        usersRepository.save(user);
    }

    @Transactional
    public void CheckForDuplicateNickname(String nickname) {
        usersRepository.findUsersByNickname(nickname).ifPresent(user -> {
            throw new CustomException(ErrorCode.ALREADY_EXIST_NICKNAME);
        });
    }

    public ResponseOAuthUserInfoDto socialUserInfo(String socialType, String code) {
        if (socialType.equals("google")) {
            return googleSocialLogin.getUserInfo(code);
        } else if (socialType.equals("kakao")) {
            return kakaoSocialLogin.getUserInfo(code);
        }
        return null;
    }
}
