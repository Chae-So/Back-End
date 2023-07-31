package com.contest.chaeso.domain.community.community.application;

import com.contest.chaeso.domain.community.category.domain.CommunityCategory;
import com.contest.chaeso.domain.community.category.domain.repository.CommunityCategoryRepository;
import com.contest.chaeso.domain.community.community.api.dto.req.RequestCommunityFormDto;
import com.contest.chaeso.domain.community.community.api.dto.res.ResponseCommunityListDto;
import com.contest.chaeso.domain.community.community.domain.Community;
import com.contest.chaeso.domain.community.community.domain.repository.CommunityQueryRepository;
import com.contest.chaeso.domain.community.community.domain.repository.CommunityRepository;
import com.contest.chaeso.domain.community.img.domain.repository.CommunityImgRepository;
import com.contest.chaeso.domain.users.users.domain.Users;
import com.contest.chaeso.domain.users.users.domain.repository.UsersRepository;
import com.contest.chaeso.domain.users.vegan.domain.VeganInfo;
import com.contest.chaeso.domain.users.vegan.domain.repository.VeganInfoRepository;
import com.contest.chaeso.global.exception.CustomException;
import com.contest.chaeso.global.exception.ErrorCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CommunityServiceTest {

    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    CommunityQueryRepository communityQueryRepositoryImpl;
    @Autowired
    CommunityImgRepository communityImgRepository;
    @Autowired
    VeganInfoRepository veganInfoRepository;
    @Autowired
    CommunityCategoryRepository categoryRepository;
    @Autowired
    UsersRepository usersRepository;

    @Test
    @DisplayName("커뮤니티 피드 리스트")
    void CommunityServiceTest() throws Exception {
        //given
        VeganInfo veganInfo = veganInfoRepository.findById(1L).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_VEGAN_INFO));
        CommunityCategory communityCategory = categoryRepository.findById(1L).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMUNITY_CATEGORY));
        Users users = usersRepository.findById(2L).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
//        for (int i = 0; i < 100; i++) {
//            communityRepository.save(new RequestCommunityFormDto("starlight.jpg", communityCategory, veganInfo, users, "서울특별시 은평구 갈현로 11길", "내용"+i).toEntity());
//        }
        //when
        List<ResponseCommunityListDto> communityCount = communityQueryRepositoryImpl.findCommunityList("BEST");
        //then

        assertThat(communityCount.size()).isEqualTo(100);
    }
}