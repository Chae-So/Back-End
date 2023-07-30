package com.contest.chaeso.domain.community.community.api.dto.req;

import com.contest.chaeso.domain.community.category.domain.CommunityCategoryType;
import com.contest.chaeso.domain.users.vegan.domain.VeganLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class RequestCommunityFormDto {

    private String imgUrl;
    private CommunityCategoryType communityCategoryType;
    private VeganLevel veganLevel;
}
