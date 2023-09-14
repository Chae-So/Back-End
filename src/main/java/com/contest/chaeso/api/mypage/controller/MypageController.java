package com.contest.chaeso.api.mypage.controller;

import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info.RestaurantMainInfoListResDto;
import com.contest.chaeso.domain.restaurant.restaurant.application.RestaurantService;
import com.contest.chaeso.global.resolver.UserInfoFromHeader;
import com.contest.chaeso.global.resolver.UserInfoFromHeaderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mypage")
public class MypageController {

    private final RestaurantService restaurantService;
    private static final int IMG_FLAG = 1;

    /**
     * bookmark restaurant list
     * @return
     */
    @GetMapping("/bookmark")
    public ResponseEntity<RestaurantMainInfoListResDto> getMyBookmark(@UserInfoFromHeader UserInfoFromHeaderDto userInfoFromHeaderDto){
        /** userId 받아와야 함 */
        RestaurantMainInfoListResDto mainResponseDto = restaurantService.getBookmarkRestaurantList(userInfoFromHeaderDto.getEmail(), IMG_FLAG);

        return new ResponseEntity<>(mainResponseDto, HttpStatus.OK);
    }
}
