package com.contest.chaeso.domain.community.community.api.dto.req;

import com.contest.chaeso.domain.community.category.domain.CommunityCategory;
import com.contest.chaeso.domain.community.category.domain.CommunityCategoryType;
import com.contest.chaeso.domain.community.community.domain.Community;
import com.contest.chaeso.domain.community.img.domain.CommunityImg;
import com.contest.chaeso.domain.users.users.domain.Users;
import com.contest.chaeso.domain.users.vegan.domain.VeganInfo;
import com.contest.chaeso.domain.users.vegan.domain.VeganLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class RequestCommunityFormDto {

    private List<String> imgUrl = new ArrayList<>();
    private CommunityCategory communityCategory;
    private VeganInfo veganInfo;
    private Users users;
    private String location;
    private String contents;

    public Community toEntity() {
        return Community.builder()
                .writer(users.getNickname())
                .contents(contents)
                .veganInfo(veganInfo)
                .communityCategory(communityCategory)
                .location(location)
                .users(users)
                .build();
    }

}
