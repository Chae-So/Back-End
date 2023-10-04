package com.contest.chaeso.domain.restaurant.review.review.domain.repository;

import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info.RestaurantReviewImgListDto;
import com.contest.chaeso.domain.restaurant.review.review.api.dto.res.RestaurantScoreCountInterface;
import com.contest.chaeso.domain.restaurant.review.review.domain.RestaurantReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantReviewRepository extends JpaRepository<RestaurantReview, Long> {


//    public List<RestaurantReview> findRestaurantReviewByRestaurant_RtId(@Param("rtId") Long rtId); // left join
//
//    public List<RestaurantReview> findRestaurantReviewByRestaurant(Restaurant restaurant); // where

    @Query("select distinct rr " +
            "from RestaurantReview rr " +
            "join fetch rr.users " +
            "join fetch rr.restaurantReviewImgList " +
            "where rr.restaurant.rtId = :rtId"
    )
    public List<RestaurantReview> findRestaurantReviewByRestaurant(@Param("rtId") Long rtId);

    @Query(value = "select score , count(*) as cnt " +
            "from restaurant_review " +
            "where rt_id = :rtId " +
            "group by score " +
            "order by score asc", nativeQuery = true)
    public List<RestaurantScoreCountInterface> findRestaurantScoreCount(@Param("rtId") Long rtId);

    @Query("select rr" +
            " from RestaurantReview rr" +
            " join fetch rr.restaurant" +
            " join fetch rr.restaurantReviewImgList")
    public List<RestaurantReview> findRestaurantReviewByImgByRestaurant();


    @Query("select new com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info.RestaurantReviewImgListDto(rri.rtReviewImgLink)" +
            " from RestaurantReview rr" +
            " join rr.restaurantReviewImgList rri" +
            " where rr.restaurant.rtId = :rtId")
    public List<RestaurantReviewImgListDto> getRestaurantReviewImgByRtId(@Param("rtId") Long rtId);


}
