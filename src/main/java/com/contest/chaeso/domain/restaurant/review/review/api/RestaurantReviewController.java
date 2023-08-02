package com.contest.chaeso.domain.restaurant.review.review.api;

import com.contest.chaeso.domain.restaurant.review.review.api.dto.req.RestaurantReviewReqDto;
import com.contest.chaeso.domain.restaurant.review.review.api.dto.res.RestaurantReviewListResDto;
import com.contest.chaeso.domain.restaurant.review.review.application.RestaurantReviewService;
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

    @GetMapping("/{rtId}")
    public ResponseEntity<RestaurantReviewListResDto> getReview(@PathVariable("rtId") Long rtId){
        RestaurantReviewListResDto restaurantReviewList = restaurantReviewService.getRestaurantReviewList(rtId);

        return new ResponseEntity<>(restaurantReviewList,HttpStatus.OK);
    }


    @PostMapping("/{rtId}")
    public ResponseEntity<String> saveReview(@PathVariable("rtId") Long rtId,
                           @RequestPart RestaurantReviewReqDto restaurantReviewReqDto,
                           @RequestPart(required = false) List<MultipartFile> files){

        Long userId = 1L; /** 나중에 바꿔주기 */

        restaurantReviewService.saveRestaurantReview(rtId, userId, restaurantReviewReqDto, files);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @PutMapping("/{rtReviewId}")
    public ResponseEntity<String> updateReview(@PathVariable("rtReviewId") Long rtReviewId,
                           @RequestPart RestaurantReviewReqDto restaurantReviewReqDto,
                           @RequestPart(required = false) List<MultipartFile> files){

        restaurantReviewService.updateRestaurantReview(rtReviewId, restaurantReviewReqDto, files);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping("/{rtReviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("rtReviewId") Long rtReviewId){

        restaurantReviewService.deleteRestaurantReview(rtReviewId);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}
