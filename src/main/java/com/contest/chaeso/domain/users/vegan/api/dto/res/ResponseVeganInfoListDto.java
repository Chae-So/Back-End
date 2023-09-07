package com.contest.chaeso.domain.users.vegan.api.dto.res;

import com.contest.chaeso.domain.users.vegan.domain.VeganInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ResponseVeganInfoListDto {

    private Long veganId;
    private String veganType;
    private String veganLevel;

    public static ResponseVeganInfoListDto veganInfoList(VeganInfo veganInfo) {
        return ResponseVeganInfoListDto.builder()
                .veganId(veganInfo.getId())
                .veganType(veganInfo.getLevel().name())
                .veganLevel(veganInfo.getLevel().getName())
                .build();
    }
}
