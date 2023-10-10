package com.contest.chaeso.domain.users.language.api;

import com.contest.chaeso.domain.users.language.api.dto.res.ResponseLanguageListDto;
import com.contest.chaeso.domain.users.language.application.LanguageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(value = "LanguageController")
@RequestMapping("/api/language")
public class LanguageController {

    private final LanguageService languageService;

    /**
     * 언어 정보 리스트 출력
     * @return List<ResponseLanguageListDto>
     */
    @ApiOperation(value = "언어 정보 조회", notes = "회원가입 전 언어정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    @GetMapping
    public List<ResponseLanguageListDto> languageInfo() {
        return languageService.languageInfo();
    }
}
