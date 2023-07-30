package com.contest.chaeso.domain.community.category.api.dto.res;

import com.contest.chaeso.domain.community.category.domain.CommunityCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ResponseCommunityCategoryListDto {

    private Long categoryId;
    private String categoryName;

    public static ResponseCommunityCategoryListDto communityList(CommunityCategory category) {
        return ResponseCommunityCategoryListDto.builder()
                .categoryId(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }

}
