package com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantImgListDto {

    private String rtImgLink;

    public RestaurantImgListDto(String rtImgLink) {
        this.rtImgLink = rtImgLink;
    }
}
