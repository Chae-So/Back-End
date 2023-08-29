package com.contest.chaeso.domain.restaurant.restaurant.api.dto.res;

import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantMainInfoResInterface {

    String getRtId();
    String getName();
    String getCategory();
    Integer getDays();
    String getEndTime();
    String getAvgScore();
    Integer getBookmark();
    String getImgLink();
    int getFlag();
    BigDecimal getCorpLon();
    BigDecimal getCorpLat();


}
