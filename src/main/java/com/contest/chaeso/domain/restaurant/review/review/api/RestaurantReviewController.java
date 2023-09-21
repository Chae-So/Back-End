package com.contest.chaeso.domain.restaurant.review.review.api;

import com.contest.chaeso.domain.restaurant.review.review.api.dto.req.RestaurantReviewReqDto;
import com.contest.chaeso.domain.restaurant.review.review.api.dto.res.RestaurantReviewListResDto;
import com.contest.chaeso.domain.restaurant.review.review.application.RestaurantReviewService;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/restaurant/review")
public class RestaurantReviewController {

    private final RestaurantReviewService restaurantReviewService;

    @ApiOperation(value = "레스토랑 리뷰 리스트 조회 api", notes = "레스토랑 리뷰 리스트를 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/{rtId}")
    public ResponseEntity<RestaurantReviewListResDto> getReview(@PathVariable("rtId") Long rtId){
        RestaurantReviewListResDto restaurantReviewList = restaurantReviewService.getRestaurantReviewList(rtId);

        return new ResponseEntity<>(restaurantReviewList,HttpStatus.OK);
    }


    @ApiOperation(value = "레스토랑 리뷰 등록 api", notes = "레스토랑 리뷰를 등록한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "저장 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @PostMapping("/{rtId}")
    public ResponseEntity<String> saveReview(@PathVariable("rtId") Long rtId,
                                             @RequestPart RestaurantReviewReqDto restaurantReviewReqDto,
                                             @RequestPart(required = false) List<MultipartFile> files,
                                             @UserInfoFromHeader UserInfoFromHeaderDto userInfoFromHeaderDto){


        restaurantReviewService.saveRestaurantReview(rtId, userInfoFromHeaderDto.getEmail(), restaurantReviewReqDto, files);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    @ApiOperation(value = "레스토랑 리뷰 수정 api", notes = "레스토랑 리뷰를 수정하다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @PutMapping("/{rtReviewId}")
    public ResponseEntity<String> updateReview(@PathVariable("rtReviewId") Long rtReviewId,
                                               @RequestPart RestaurantReviewReqDto restaurantReviewReqDto,
                                               @RequestPart(required = false) List<MultipartFile> files){

        restaurantReviewService.updateRestaurantReview(rtReviewId, restaurantReviewReqDto, files);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    @ApiOperation(value = "레스토랑 리뷰 삭제 api", notes = "레스토랑 리뷰를 삭제한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "삭제 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @DeleteMapping("/{rtReviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("rtReviewId") Long rtReviewId){

        restaurantReviewService.deleteRestaurantReview(rtReviewId);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}
