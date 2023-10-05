package com.contest.chaeso.domain.users.users.domain.repository;

import com.contest.chaeso.domain.users.users.domain.SocialType;
import com.contest.chaeso.domain.users.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findUsersByNickname(String nickname);

    Optional<Users> findByEmail(String email);

    Optional<Users> findByRefreshToken(String refreshToken);

    Optional<Users> findBySocialTypeAndSocialId(SocialType socialType, String socialId);

    Optional<Users> findBySocialId(String socialId);
}
