package com.contest.chaeso.domain.users.users.domain.repository;

import com.contest.chaeso.domain.users.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
