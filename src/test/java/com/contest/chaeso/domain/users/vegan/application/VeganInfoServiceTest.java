package com.contest.chaeso.domain.users.vegan.application;

import com.contest.chaeso.domain.users.vegan.api.dto.res.ResponseVeganInfoListDto;
import com.contest.chaeso.domain.users.vegan.domain.repository.VeganInfoRepository;
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
class VeganInfoServiceTest {

    @Autowired
    VeganInfoRepository veganInfoRepository;

    @Test
    @DisplayName("비건 단계 출력")
    void veganInfoList() {
        List<ResponseVeganInfoListDto> veganInfoList = veganInfoRepository.findAll().stream()
                .map(ResponseVeganInfoListDto::veganInfoList)
                .collect(Collectors.toList());

        assertThat(veganInfoList.size()).isEqualTo(5);
    }

}