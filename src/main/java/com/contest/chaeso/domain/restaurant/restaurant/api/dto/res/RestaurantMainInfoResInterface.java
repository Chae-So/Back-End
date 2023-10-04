package com.contest.chaeso.domain.restaurant.restaurant.api.dto.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

public interface RestaurantMainInfoResInterface {

    String getRtId();
    String getName();
    String getCategory();
    Integer getDays();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "kk:mm:ss", timezone = "Asia/Seoul")
    LocalTime getEndTime();
    Float getAvgScore();
    Integer getBookmark();
    String getImgLink();
    int getFlag();
    BigDecimal getCorpLon();
    BigDecimal getCorpLat();


}
