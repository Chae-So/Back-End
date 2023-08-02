package com.contest.chaeso.domain.restaurant.restaurant.api.dto.res;

import org.springframework.lang.Nullable;

import java.util.List;

public interface RestaurantMainInfoResInterface {

    String getRtId();
     String getName();
     String getCategory();
     Integer getDays();
     String getEndTime();
     String getAvgScore();
     Integer getBookmark();
//     @Nullable
//     List<RestaurantImgDto> getImgList();

}
