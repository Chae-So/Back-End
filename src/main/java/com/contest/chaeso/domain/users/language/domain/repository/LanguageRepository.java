package com.contest.chaeso.domain.users.language.domain.repository;

import com.contest.chaeso.domain.users.language.domain.Language;
import com.contest.chaeso.domain.users.language.domain.LanguageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<LanguageInfo, Long> {
}
