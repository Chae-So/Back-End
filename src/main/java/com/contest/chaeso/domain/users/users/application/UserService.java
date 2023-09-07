package com.contest.chaeso.domain.users.users.application;

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
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final LanguageRepository languageRepository;
    private final VeganInfoRepository veganInfoRepository;

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
                .languageInfo(findLanguage)
                .veganInfo(findVegan)
                .role(Role.USER)
                .build();

        user.passwordEncode(passwordEncoder);
        usersRepository.save(user);
    }
}
