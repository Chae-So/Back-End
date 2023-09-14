package com.contest.chaeso.domain.restaurant.restaurant.application;

import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info.RestaurantImgListDto;
import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info.RestaurantInfoResDto;
import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.menu.RestaurantMenuInfoDto;
import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.menu.RestaurantMenuResListDto;
import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info.RestaurantInfoDto;
import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.RestaurantMainInfoResInterface;
import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info.RestaurantMainInfoListResDto;
import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import com.contest.chaeso.domain.restaurant.restaurant.domain.repository.RestaurantRepository;
import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info.RestaurantReviewImgListDto;
import com.contest.chaeso.domain.restaurant.review.review.domain.repository.RestaurantReviewRepository;
import com.contest.chaeso.domain.users.users.domain.Users;
import com.contest.chaeso.domain.users.users.domain.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantReviewRepository restaurantReviewRepository;
    private final UsersRepository usersRepository;

    /**
     * main restaurant List 가져오기
     * @param email
     * @return
     */
    public RestaurantMainInfoListResDto getRestaurantMainInfoList(String email, int flag){
        Users users = usersRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("email 존재하지 않습니다."));

        int today = getToday();
        List<RestaurantMainInfoResInterface> restaurantInfo = restaurantRepository.findRestaurantList(users.getUserId(), today, flag);
        RestaurantMainInfoListResDto mainResponseDto = new RestaurantMainInfoListResDto(restaurantInfo);

        return mainResponseDto;
    }


    /**
     * 현위치 반경 500m main restaurnt List 가져오기
     * @param email
     * @param flag
     * @return
     */
    public RestaurantMainInfoListResDto getRestaurantMainInfoListByMyPosition(String email, int flag, BigDecimal myLon, BigDecimal myLat, int range){
        Users users = usersRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("email 존재하지 않습니다."));
        int today = getToday();
        List<RestaurantMainInfoResInterface> restaurantInfo = restaurantRepository.findRestaurantListByMyPosition(users.getUserId(), today, flag, myLon, myLat, range);
        RestaurantMainInfoListResDto mainResponseDto = new RestaurantMainInfoListResDto(restaurantInfo);

        return mainResponseDto;
    }

    /**
     * category main restaurant List 가져오기
     * @param email
     * @param category
     * @param flag
     * @return
     */
    public RestaurantMainInfoListResDto findRestaurantByCategory(String email, String category, int flag){
        Users users = usersRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("email 존재하지 않습니다."));
        int today = getToday();
        List<RestaurantMainInfoResInterface> restaurantInfo = restaurantRepository.findRestaurantByCategory(users.getUserId(), today, category,flag);


        RestaurantMainInfoListResDto mainResponseDto = new RestaurantMainInfoListResDto(restaurantInfo);

        return mainResponseDto;
    }

    /**
     * 현위치 반경 500m category main restaurant List 가져오기
     * @param email
     * @param category
     * @param flag
     * @return
     */
    public RestaurantMainInfoListResDto findRestaurantByCategoryAndMyPosition(String email, String category, int flag, BigDecimal myLon, BigDecimal myLat, int range){
        Users users = usersRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("email 존재하지 않습니다."));

        int today = getToday();
        List<RestaurantMainInfoResInterface> restaurantInfo = restaurantRepository.findRestaurantByCategoryAndMyPosition(users.getUserId(), today, category,flag, myLon, myLat, range);


        RestaurantMainInfoListResDto mainResponseDto = new RestaurantMainInfoListResDto(restaurantInfo);

        return mainResponseDto;
    }


    /**
     * main restaurant info 가져오기
     * restrant 사진 및 review 사진 가져오기
     * @return
     */
    public RestaurantInfoResDto findRestaurantInfo(Long rtId){
        // restaurant menu
        Restaurant restaurant = restaurantRepository.findRestaurantBzhByRtId(rtId);
        RestaurantInfoDto restaurantInfoResDto = new RestaurantInfoDto(restaurant);

        // restaurant images
        List<RestaurantImgListDto> restaurantImgByRtId = restaurantRepository.getRestaurantImgByRtId(rtId);
        // restaurant review images
        List<RestaurantReviewImgListDto> restaurantReviewImgByRtId = restaurantReviewRepository.getRestaurantReviewImgByRtId(rtId);

        return new RestaurantInfoResDto(restaurantInfoResDto, restaurantImgByRtId, restaurantReviewImgByRtId);
    }

    /**
     * main restaurant menu 가져오기
     * @param rtId
     * @return
     */
    public RestaurantMenuResListDto findRestaurantMenuByRestaurant(Long rtId){
        Restaurant restaurantMenuByRestaurant = restaurantRepository.findRestaurantMenuByRtId(rtId);
        List<RestaurantMenuInfoDto> collect = restaurantMenuByRestaurant.getRestaurantMenuList().stream().map(RestaurantMenuInfoDto::new).collect(Collectors.toList());
        RestaurantMenuResListDto restaurantMenuResListDto = new RestaurantMenuResListDto(collect);

        return restaurantMenuResListDto;

    }

    /**
     * mypage 나의 북마크 레스토랑 리스트
     * @return
     */
    public RestaurantMainInfoListResDto getBookmarkRestaurantList(String email, int flag){
        Users users = usersRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("email 존재하지 않습니다."));
        int today = getToday();

        List<RestaurantMainInfoResInterface> restaurantInfo = restaurantRepository.findMyBookmarkRestaurantList(users.getUserId(), today, flag);
        RestaurantMainInfoListResDto mainResponseDto = new RestaurantMainInfoListResDto(restaurantInfo);

        return mainResponseDto;
    }



    private int getToday(){
        LocalDate now = LocalDate.now();

        return now.getDayOfWeek().getValue();
    }

    private void haveRestaurntMainImg(){

    }

}

