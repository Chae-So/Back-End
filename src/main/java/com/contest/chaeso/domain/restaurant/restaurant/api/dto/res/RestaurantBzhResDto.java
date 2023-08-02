package com.contest.chaeso.domain.restaurant.restaurant.api.dto.res;

import com.contest.chaeso.domain.restaurant.bzhour.domain.RestaurantBzh;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantBzhResDto {

    private Integer days;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "kk:mm:ss", timezone = "Asia/Seoul")
    private LocalTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "kk:mm:ss", timezone = "Asia/Seoul")
    private LocalTime endTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "kk:mm:ss", timezone = "Asia/Seoul")
    private LocalTime breakTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "kk:mm:ss", timezone = "Asia/Seoul")
    private LocalTime lastOrderTime;

    public RestaurantBzhResDto(RestaurantBzh restaurantBzh) {
        this.days = restaurantBzh.getDays();
        this.startTime = restaurantBzh.getStartTime();
        this.endTime = restaurantBzh.getEndTime();
        this.breakTime = restaurantBzh.getBreakTime();
        this.lastOrderTime = restaurantBzh.getLastOrderTime();
    }
}
