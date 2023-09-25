package com.contest.chaeso.domain.restaurant.review.review.application;

import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import com.contest.chaeso.domain.restaurant.restaurant.domain.repository.RestaurantRepository;
import com.contest.chaeso.domain.restaurant.review.img.domain.RestaurantReviewImg;
import com.contest.chaeso.domain.restaurant.review.img.domain.repository.RestaurantReviewImgRepository;
import com.contest.chaeso.domain.restaurant.review.review.api.dto.req.RestaurantReviewReqDto;
import com.contest.chaeso.domain.restaurant.review.review.api.dto.res.RestaurantReviewImgDto;
import com.contest.chaeso.domain.restaurant.review.review.api.dto.res.RestaurantReviewListResDto;
import com.contest.chaeso.domain.restaurant.review.review.api.dto.res.RestaurantReviewDto;
import com.contest.chaeso.domain.restaurant.review.review.api.dto.res.RestaurantScoreCountInterface;
import com.contest.chaeso.domain.restaurant.review.review.domain.RestaurantReview;
import com.contest.chaeso.domain.restaurant.review.review.domain.repository.RestaurantReviewRepository;
import com.contest.chaeso.domain.users.users.domain.Users;
import com.contest.chaeso.domain.users.users.domain.repository.UsersRepository;
import com.contest.chaeso.global.exception.CustomException;
import com.contest.chaeso.global.exception.ErrorCode;
import com.contest.chaeso.global.util.aws.s3.service.AmazonS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class RestaurantReviewService {

    private final RestaurantReviewRepository restaurantReviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UsersRepository usersRepository;
    private final RestaurantReviewImgRepository restaurantReviewImgRepository;
    private final AmazonS3Service amazonS3Service;


    public RestaurantReviewListResDto getRestaurantReviewList(Long rtId){
        // review List
        List<RestaurantReview> restaurantReviewByRestaurant = restaurantReviewRepository.findRestaurantReviewByRestaurant(rtId);

        List<RestaurantReviewDto> restaurantReviewList = restaurantReviewByRestaurant.stream().map(RestaurantReviewDto::new).collect(Collectors.toList());

        // score count
        List<RestaurantScoreCountInterface> restaurantScoreCount = restaurantReviewRepository.findRestaurantScoreCount(rtId);

        return new RestaurantReviewListResDto(restaurantReviewList, restaurantScoreCount);

    }

    public void saveRestaurantReview(Long rtId, String email, RestaurantReviewReqDto restaurantReviewReqDto, List<MultipartFile> files) {
        Restaurant restaurant = restaurantRepository.findById(rtId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESTAURANT));
        Users users = usersRepository.findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        // restaurant review 생성
        RestaurantReview restaurantReview = RestaurantReview.createRestaurantReview(restaurantReviewReqDto, users, restaurant);

        // S3에서 링크 받아와서 db 넣어주에
        for (MultipartFile file : files) {
            String fileUrl = amazonS3Service.uploadFile(file);
            RestaurantReviewImg restaurantReviewImg = RestaurantReviewImg.createRestaurantReviewImgWithCascade(fileUrl);
            restaurantReview.addRestaurantReviewImg(restaurantReviewImg);
        }

        // 저장
        restaurantReviewRepository.save(restaurantReview);

    }

    public void updateRestaurantReview(Long rtReviewId, RestaurantReviewReqDto restaurantReviewReqDto, List<MultipartFile> files) {
        RestaurantReview restaurantReview = restaurantReviewRepository.findById(rtReviewId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESTAURANT_REVIEW));

        // db에서 가져오고 s3에서 삭제
        List<RestaurantReviewImgDto> imgLinkList = restaurantReviewImgRepository.findRestaurantReviewImgByRtReviewId(restaurantReview.getRtReviewId());
        for (RestaurantReviewImgDto restaurantReviewImgDto : imgLinkList) {
            amazonS3Service.deleteFile(restaurantReviewImgDto.getRtReviewImgLink());

        }

        // db에서도 삭제
        restaurantReviewImgRepository.deleteByRestaurantReview(rtReviewId);

        // s3에 다시 저장하고 링크 받아와서 db에 넣어주기
        for (MultipartFile file : files) {
            String fileUrl = amazonS3Service.uploadFile(file);
            RestaurantReviewImg restaurantReviewImg = RestaurantReviewImg.createRestaurantReviewImgWithCascade(fileUrl);
            restaurantReview.addRestaurantReviewImg(restaurantReviewImg);
        }

        restaurantReview.updateReview(restaurantReviewReqDto);

    }

    public void deleteRestaurantReview(Long rtReviewId) {

        restaurantReviewRepository.deleteById(rtReviewId);

    }
}
