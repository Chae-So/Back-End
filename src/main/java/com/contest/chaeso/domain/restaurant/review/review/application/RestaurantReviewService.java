package com.contest.chaeso.domain.restaurant.review.review.application;

import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import com.contest.chaeso.domain.restaurant.restaurant.domain.repository.RestaurantRepository;
import com.contest.chaeso.domain.restaurant.review.img.domain.RestaurantReviewImg;
import com.contest.chaeso.domain.restaurant.review.img.domain.repository.RestaurantReviewImgRepository;
import com.contest.chaeso.domain.restaurant.review.review.api.dto.req.RestaurantReviewReqDto;
import com.contest.chaeso.domain.restaurant.review.review.api.dto.res.RestaurantReviewListResDto;
import com.contest.chaeso.domain.restaurant.review.review.api.dto.res.RestaurantReviewDto;
import com.contest.chaeso.domain.restaurant.review.review.api.dto.res.RestaurantScoreCountInterface;
import com.contest.chaeso.domain.restaurant.review.review.domain.RestaurantReview;
import com.contest.chaeso.domain.restaurant.review.review.domain.repository.RestaurantReviewRepository;
import com.contest.chaeso.domain.users.users.domain.Users;
import com.contest.chaeso.domain.users.users.domain.repository.UsersRepository;
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


    public RestaurantReviewListResDto getRestaurantReviewList(Long rtId){
        // review List
        List<RestaurantReview> restaurantReviewByRestaurant = restaurantReviewRepository.findRestaurantReviewByRestaurant(rtId);

        List<RestaurantReviewDto> restaurantReviewList = restaurantReviewByRestaurant.stream().map(RestaurantReviewDto::new).collect(Collectors.toList());

        // score count
        List<RestaurantScoreCountInterface> restaurantScoreCount = restaurantReviewRepository.findRestaurantScoreCount(rtId);

        return new RestaurantReviewListResDto(restaurantReviewList, restaurantScoreCount);

    }

    public void saveRestaurantReview(Long rtId, String email, RestaurantReviewReqDto restaurantReviewReqDto, List<MultipartFile> files) {
        Restaurant restaurant = restaurantRepository.findById(rtId).get();
        Users users = usersRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("email 존재하지 않습니다."));

        // restaurant review 생성
        RestaurantReview restaurantReview = RestaurantReview.createRestaurantReview(restaurantReviewReqDto, users, restaurant);

        // S3에서 링크 받아와서 넣어주기
        for (MultipartFile file : files) {
            
        }
        RestaurantReviewImg restaurantReviewImg = RestaurantReviewImg.createRestaurantReviewImgWithCascade(files.get(0).getOriginalFilename());
        RestaurantReviewImg restaurantReviewImg2 = RestaurantReviewImg.createRestaurantReviewImgWithCascade(files.get(1).getOriginalFilename());

        // review img 추가
        restaurantReview.addRestaurantReviewImg(restaurantReviewImg);
        restaurantReview.addRestaurantReviewImg(restaurantReviewImg2);

        // 저장
        restaurantReviewRepository.save(restaurantReview);


    }

    public void updateRestaurantReview(Long rtReviewId, RestaurantReviewReqDto restaurantReviewReqDto, List<MultipartFile> files) {
        RestaurantReview restaurantReview = restaurantReviewRepository.findById(rtReviewId).get();

        // S3에서 삭제 후 다시 저장하고 링크 받아와서 넣어주기
        RestaurantReviewImg restaurantReviewImg = RestaurantReviewImg.createRestaurantReviewImgWithCascade(files.get(0).getOriginalFilename());
        RestaurantReviewImg restaurantReviewImg2 = RestaurantReviewImg.createRestaurantReviewImgWithCascade(files.get(1).getOriginalFilename());

        // list를 s3에서 받아오면 updateReview에 imgList 넣어줄 필요 없음
        List<RestaurantReviewImg> restaurantReviewImgList = new ArrayList<>();
        restaurantReviewImgList.add(restaurantReviewImg);
        restaurantReviewImgList.add(restaurantReviewImg2);

        // db에서도 삭제하고 다시 넣어주기
        restaurantReviewImgRepository.deleteByRestaurantReview(rtReviewId);

        restaurantReview.updateReview(restaurantReviewReqDto, restaurantReviewImgList);

    }

    public void deleteRestaurantReview(Long rtReviewId) {

        restaurantReviewRepository.deleteById(rtReviewId);

    }
}
