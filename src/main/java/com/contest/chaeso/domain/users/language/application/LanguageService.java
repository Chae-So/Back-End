package com.contest.chaeso.domain.users.language.application;

import com.contest.chaeso.domain.users.language.api.dto.res.ResponseLanguageListDto;
import com.contest.chaeso.domain.users.language.domain.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LanguageService {

    private final LanguageRepository languageRepository;

    public List<ResponseLanguageListDto> languageInfo() {
        return languageRepository.findAll().stream()
                .map(ResponseLanguageListDto::languageListInfo)
                .collect(Collectors.toList());
    }
}
