package com.contest.chaeso.domain.restaurant.restaurant.api;


import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info.RestaurantInfoResDto;
import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.menu.RestaurantMenuResListDto;
import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info.RestaurantInfoDto;
import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info.RestaurantMainInfoListResDto;
import com.contest.chaeso.domain.restaurant.restaurant.application.RestaurantService;
import com.contest.chaeso.global.resolver.UserInfoFromHeader;
import com.contest.chaeso.global.resolver.UserInfoFromHeaderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    private final static int IMG_FLAG = 1;
    private final static int RANGE = 500;

    /**
     * restaurnt list get api
     * 평균점수 순으로 정렬(default)
     * @return
     */
    @GetMapping(value = {"/main", "/main/sort"})
    public ResponseEntity<RestaurantMainInfoListResDto> getRestaurantList(@UserInfoFromHeader UserInfoFromHeaderDto userInfoFromHeaderDto){
        RestaurantMainInfoListResDto mainResponseDto = restaurantService.getRestaurantMainInfoList(userInfoFromHeaderDto.getEmail(), IMG_FLAG);

        return new ResponseEntity<>(mainResponseDto, HttpStatus.OK);
    }

    /**
     * 현위치 기반 restaurnt list get api
     * 평균점수로
     * @return
     */
    @GetMapping(value = {"/main/mp"})
    public ResponseEntity<RestaurantMainInfoListResDto> getRestaurantListByMyPosition(@RequestParam("myLon") BigDecimal myLon,
                                                                                      @RequestParam("myLat") BigDecimal myLat,
                                                                                      @UserInfoFromHeader UserInfoFromHeaderDto userInfoFromHeaderDto){
        RestaurantMainInfoListResDto mainResponseDto = restaurantService.getRestaurantMainInfoListByMyPosition(userInfoFromHeaderDto.getEmail(), IMG_FLAG, myLon, myLat, RANGE);

        return new ResponseEntity<>(mainResponseDto, HttpStatus.OK);
    }


    /**
     * category에 따른 restaurant list get api
     * @param category
     * @return
     */
    @GetMapping("/main/{category}")
    public  ResponseEntity<RestaurantMainInfoListResDto> getRestaurantListByCategory(@PathVariable("category") String category,
                                                                                     @UserInfoFromHeader UserInfoFromHeaderDto userInfoFromHeaderDto){

        RestaurantMainInfoListResDto mainCategoryResponseDto = restaurantService.findRestaurantByCategory(userInfoFromHeaderDto.getEmail(), category, IMG_FLAG);

        return new ResponseEntity<>(mainCategoryResponseDto, HttpStatus.OK);
    }

    /**
     * 현위치 기반 category에 따른 restaurant list get api
     * @param category
     * @return
     */
    @GetMapping("/main/{category}/mp")
    public  ResponseEntity<RestaurantMainInfoListResDto> getRestaurantListByCategoryAndMyPosition(@PathVariable("category") String category,
                                                                                                  @RequestParam("myLon") BigDecimal myLon,
                                                                                                  @RequestParam("myLat") BigDecimal myLat,
                                                                                                  @UserInfoFromHeader UserInfoFromHeaderDto userInfoFromHeaderDto){

        RestaurantMainInfoListResDto mainCategoryResponseDto = restaurantService.findRestaurantByCategoryAndMyPosition(userInfoFromHeaderDto.getEmail(), category, IMG_FLAG, myLon, myLat, RANGE);

        return new ResponseEntity<>(mainCategoryResponseDto, HttpStatus.OK);
    }

    /**
     * restaurnt info(bzh) get api
     * @param rtId
     * @return
     */
    @GetMapping("/{rtId}/info")
    /**
     * 여기에 레스토랑 사진이랑 review 사진들 퍼오기
     */
    public ResponseEntity<RestaurantInfoResDto> getRestaurantInfo(@PathVariable(name = "rtId") Long rtId) {

        RestaurantInfoResDto restaurantInfoByRtId = restaurantService.findRestaurantInfo(rtId);

        return new ResponseEntity<>(restaurantInfoByRtId, HttpStatus.OK);
    }

    /**
     * restaurant menu get api
     * @param rtId
     * @return
     */
    @GetMapping("/{rtId}/menu")
    public ResponseEntity<RestaurantMenuResListDto> getRestaurantMenuInfo(@PathVariable(name = "rtId") Long rtId){

        RestaurantMenuResListDto restaurantMenuByRestaurant = restaurantService.findRestaurantMenuByRestaurant(rtId);

        return new ResponseEntity<>(restaurantMenuByRestaurant, HttpStatus.OK);
    }



}
