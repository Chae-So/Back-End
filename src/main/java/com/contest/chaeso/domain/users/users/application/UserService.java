package com.contest.chaeso.domain.users.users.application;

import com.contest.chaeso.domain.users.users.api.dto.req.RequestUserSignUpDto;
import com.contest.chaeso.domain.users.users.domain.Role;
import com.contest.chaeso.domain.users.users.domain.Users;
import com.contest.chaeso.domain.users.users.domain.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(RequestUserSignUpDto userSignUpDto) throws Exception {

        if (usersRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        if (usersRepository.findUsersByNickname(userSignUpDto.getNickname()).isPresent()) {
            throw new Exception("이미 존재하는 닉네임입니다.");
        }

        Users user = Users.builder()
                .email(userSignUpDto.getEmail())
                .pw(userSignUpDto.getPw())
                .nickname(userSignUpDto.getNickname())
                .role(Role.USER)
                .build();

        user.passwordEncode(passwordEncoder);
        usersRepository.save(user);
    }
}
