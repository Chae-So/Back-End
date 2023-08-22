package com.contest.chaeso.domain.users.language.api.dto.res;

import com.contest.chaeso.domain.users.language.domain.LanguageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ResponseLanguageListDto {

    private String languageType;
    private String languageName;

    public static ResponseLanguageListDto languageListInfo(LanguageInfo languageInfo) {
        return ResponseLanguageListDto.builder()
                .languageType(languageInfo.getLanguage().name())
                .languageName(languageInfo.getLanguage().getName())
                .build();
    }
}
