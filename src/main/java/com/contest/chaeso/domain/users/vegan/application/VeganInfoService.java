package com.contest.chaeso.domain.users.vegan.application;

import com.contest.chaeso.domain.users.vegan.api.dto.res.ResponseVeganInfoListDto;
import com.contest.chaeso.domain.users.vegan.domain.repository.VeganInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VeganInfoService {

    private final VeganInfoRepository veganInfoRepository;

    @Transactional(readOnly = true)
    public List<ResponseVeganInfoListDto> veganInfoList() {
        return veganInfoRepository.findAll().stream()
                .map(ResponseVeganInfoListDto::veganInfoList)
                .collect(Collectors.toList());
    }
}
