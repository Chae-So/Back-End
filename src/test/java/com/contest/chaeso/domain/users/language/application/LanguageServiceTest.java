package com.contest.chaeso.domain.users.language.application;

import com.contest.chaeso.domain.users.language.api.dto.res.ResponseLanguageListDto;
import com.contest.chaeso.domain.users.language.domain.repository.LanguageRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class LanguageServiceTest {

    @Autowired
    LanguageRepository languageRepository;

    @Test
    @DisplayName("언어 정보 조회")
    void languageInfo() {
        List<ResponseLanguageListDto> languageList = languageRepository.findAll().stream()
                .map(ResponseLanguageListDto::languageListInfo)
                .collect(Collectors.toList());

        assertThat(languageList.size()).isEqualTo(8);
    }

}