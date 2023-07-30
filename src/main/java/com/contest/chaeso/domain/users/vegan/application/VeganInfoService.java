package com.contest.chaeso.domain.users.vegan.application;

import com.contest.chaeso.domain.users.vegan.api.dto.res.ResponseVeganInfoListDto;
import com.contest.chaeso.domain.users.vegan.domain.VeganInfo;
import com.contest.chaeso.domain.users.vegan.domain.VeganLevel;
import com.contest.chaeso.domain.users.vegan.domain.repository.VeganInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VeganInfoService {

    private final VeganInfoRepository veganInfoRepository;

    @Transactional(readOnly = true)
    public Map<Long, String> veganInfoList() {
        return veganInfoRepository.findAll().stream()
                .map(vegan -> ResponseVeganInfoListDto.veganInfoList(vegan))
                .collect(Collectors.toMap(ResponseVeganInfoListDto::getVeganId, ResponseVeganInfoListDto::getVeganLevel));
    }
}
