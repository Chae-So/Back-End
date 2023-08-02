package com.contest.chaeso.domain.users.vegan.domain.repository;

import com.contest.chaeso.domain.users.vegan.domain.VeganInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeganInfoRepository extends JpaRepository<VeganInfo, Long> {
}
