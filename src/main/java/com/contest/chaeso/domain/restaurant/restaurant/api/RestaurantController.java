package com.contest.chaeso.domain.restaurant.restaurant.api;


import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info.RestaurantInfoResDto;
import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.menu.RestaurantMenuResListDto;
import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info.RestaurantInfoDto;
import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info.RestaurantMainInfoListResDto;
import com.contest.chaeso.domain.restaurant.restaurant.application.RestaurantService;
import com.contest.chaeso.global.resolver.UserInfoFromHeader;
import com.contest.chaeso.global.resolver.UserInfoFromHeaderDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiOperation(value = "모든 레스토랑 리스트 조회 api", notes = "모든 레스토랑 리스트를 조회한다. 평점 내림차순 정렬")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping(value = {"/main", "/main/sort"})
    public ResponseEntity<RestaurantMainInfoListResDto> getRestaurantList(@UserInfoFromHeader UserInfoFromHeaderDto userInfoFromHeaderDto){
        RestaurantMainInfoListResDto mainResponseDto = restaurantService.getRestaurantMainInfoList(userInfoFromHeaderDto.getEmail(), IMG_FLAG);

        return new ResponseEntity<>(mainResponseDto, HttpStatus.OK);
    }

    @ApiOperation(value = "현위치 가반 500m 내 레스토랑 리스트 조회 api", notes = "현위치 기반 500m 내 레스토랑 리스트를 조회한다. 평점 내림차순 정렬")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping(value = {"/main/mp"})
    public ResponseEntity<RestaurantMainInfoListResDto> getRestaurantListByMyPosition(@RequestParam("myLon") BigDecimal myLon,
                                                                                      @RequestParam("myLat") BigDecimal myLat,
                                                                                      @UserInfoFromHeader UserInfoFromHeaderDto userInfoFromHeaderDto){
        RestaurantMainInfoListResDto mainResponseDto = restaurantService.getRestaurantMainInfoListByMyPosition(userInfoFromHeaderDto.getEmail(), IMG_FLAG, myLon, myLat, RANGE);

        return new ResponseEntity<>(mainResponseDto, HttpStatus.OK);
    }


    @ApiOperation(value = "카테고리별 레스토랑 리스트 조회 api", notes = "카테고리별 레스토랑 리스트를 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/main/{category}")
    public  ResponseEntity<RestaurantMainInfoListResDto> getRestaurantListByCategory(@PathVariable("category") String category,
                                                                                     @UserInfoFromHeader UserInfoFromHeaderDto userInfoFromHeaderDto){

        RestaurantMainInfoListResDto mainCategoryResponseDto = restaurantService.findRestaurantByCategory(userInfoFromHeaderDto.getEmail(), category, IMG_FLAG);

        return new ResponseEntity<>(mainCategoryResponseDto, HttpStatus.OK);
    }

    @ApiOperation(value = "카테고리별 현위치 가반 500m 내 레스토랑 리스트 조회 api", notes = "카테고리별 현위치 가반 500m 내 레스토랑 리스트를 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/main/{category}/mp")
    public  ResponseEntity<RestaurantMainInfoListResDto> getRestaurantListByCategoryAndMyPosition(@PathVariable("category") String category,
                                                                                                  @RequestParam("myLon") BigDecimal myLon,
                                                                                                  @RequestParam("myLat") BigDecimal myLat,
                                                                                                  @UserInfoFromHeader UserInfoFromHeaderDto userInfoFromHeaderDto){

        RestaurantMainInfoListResDto mainCategoryResponseDto = restaurantService.findRestaurantByCategoryAndMyPosition(userInfoFromHeaderDto.getEmail(), category, IMG_FLAG, myLon, myLat, RANGE);

        return new ResponseEntity<>(mainCategoryResponseDto, HttpStatus.OK);
    }

    @ApiOperation(value = "레스토랑 정보 조회 api", notes = "레스토랑 정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/{rtId}/info")
    public ResponseEntity<RestaurantInfoResDto> getRestaurantInfo(@PathVariable(name = "rtId") Long rtId) {

        RestaurantInfoResDto restaurantInfoByRtId = restaurantService.findRestaurantInfo(rtId);

        return new ResponseEntity<>(restaurantInfoByRtId, HttpStatus.OK);
    }

    @ApiOperation(value = "레스토랑 메뉴 조회 api", notes = "레스토랑 메뉴를 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/{rtId}/menu")
    public ResponseEntity<RestaurantMenuResListDto> getRestaurantMenuInfo(@PathVariable(name = "rtId") Long rtId){

        RestaurantMenuResListDto restaurantMenuByRestaurant = restaurantService.findRestaurantMenuByRestaurant(rtId);

        return new ResponseEntity<>(restaurantMenuByRestaurant, HttpStatus.OK);
    }



}
