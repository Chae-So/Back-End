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
    private String veganLevel;

    public static ResponseVeganInfoListDto veganInfoList(VeganInfo veganInfo) {
        return ResponseVeganInfoListDto.builder()
                .veganId(veganInfo.getId())
                .veganLevel(veganInfo.getLevel().getName())
                .build();
    }
}
