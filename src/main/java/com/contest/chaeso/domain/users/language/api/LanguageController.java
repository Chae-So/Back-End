package com.contest.chaeso.domain.users.language.api;

import com.contest.chaeso.domain.users.language.api.dto.res.ResponseLanguageListDto;
import com.contest.chaeso.domain.users.language.application.LanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/language")
public class LanguageController {

    private final LanguageService languageService;

    /**
     * 언어 정보 리스트 출력
     * @return List<ResponseLanguageListDto>
     */
    @GetMapping
    public List<ResponseLanguageListDto> languageInfo() {
        return languageService.languageInfo();
    }
}
