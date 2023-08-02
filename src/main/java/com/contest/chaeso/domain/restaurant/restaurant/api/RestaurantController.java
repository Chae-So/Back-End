package com.contest.chaeso.domain.restaurant.restaurant.api;


import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.RestaurantInfoResDto;
import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.RestaurantMainInfoListResDto;
import com.contest.chaeso.domain.restaurant.restaurant.application.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;


    @GetMapping(value = {"/main", "/main/sort"})
    public ResponseEntity<RestaurantMainInfoListResDto> getRestaurantMainInfoList(){
        /** userId 받아와야 함 */
        RestaurantMainInfoListResDto mainResponseDto = restaurantService.getRestaurantMainInfoList(1L);

        return new ResponseEntity<>(mainResponseDto, HttpStatus.OK);
    }

    @GetMapping("/main/{category}")
    public  ResponseEntity<RestaurantMainInfoListResDto> getRestaurantMainInfoByCategoryList(@PathVariable("category") String category){

        RestaurantMainInfoListResDto mainCategoryResponseDto = restaurantService.getRestaurantMainInfoByCategoryList(1L, category);

        return new ResponseEntity<>(mainCategoryResponseDto, HttpStatus.OK);
    }
    @GetMapping("/{rtId}/info")
    public ResponseEntity<RestaurantInfoResDto> getRestaurantInfo(@PathVariable(name = "rtId") Long rtId) {

        RestaurantInfoResDto restaurantInfoByRtId = restaurantService.findRestaurantInfo(rtId);

        return new ResponseEntity<>(restaurantInfoByRtId, HttpStatus.OK);
    }


    /**
     * 카테고리별 가져오는거 -> oK
     * main 뿌려주는 거 식당의 이미지가 있으면 그거 주는데, 아니면 review 이미지 주기
     * table에 전화번호 추가해야함
     */


}
