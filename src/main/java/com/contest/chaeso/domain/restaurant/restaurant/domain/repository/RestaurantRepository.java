package com.contest.chaeso.domain.restaurant.restaurant.domain.repository;

import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.RestaurantMainInfoResInterface;
import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // restaurant 메인화면 리스트만 가져오기(간략정보)
    @Query(value = "select r.rt_id as rtId, r.Name as name, r.category as category, bzh.days as days, bzh.end_time as endTime, rr.avg_score as avgScore, rb.user_id as bookmark" +
            " FROM restaurant r" +
            " left join (select rb.rt_id, rb.user_id from restaurant_bookmark rb where rb.user_id = :userId) as rb on r.rt_id = rb.rt_id" +
            " left join (select rt_id, avg(score) as avg_score from restaurant_review  group by rt_id) rr on r.rt_id = rr.rt_id" +
            " left join (select bzh.rt_id, bzh.end_time, bzh.days from restaurant_bzh bzh where bzh.days = :days) bzh on r.rt_id = bzh.rt_id" +
            " order by avg_score desc", nativeQuery = true)
    public List<RestaurantMainInfoResInterface> findRestaurantMainInfoList(@Param("userId") Long userId, @Param("days") int days);


    // restaurnat category 메인화면 리스트 가져오기
    @Query(value = "select r.rt_id as rtId, r.Name as name, r.category as category, bzh.days as days, bzh.end_time as endTime, rr.avg_score as avgScore, rb.user_id as bookmark" +
            " FROM restaurant r" +
            " left join (select rb.rt_id, rb.user_id from restaurant_bookmark rb where rb.user_id = :userId) as rb on r.rt_id = rb.rt_id" +
            " left join (select rt_id, avg(score) as avg_score from restaurant_review  group by rt_id) rr on r.rt_id = rr.rt_id" +
            " left join (select bzh.rt_id, bzh.end_time, bzh.days from restaurant_bzh bzh where bzh.days = :days) bzh on r.rt_id = bzh.rt_id" +
            " where r.category = :category" +
            " order by avg_score desc", nativeQuery = true)
    public List<RestaurantMainInfoResInterface> findRestaurantMainInfoByCategoryList(@Param("userId") Long userId,
                                                                                     @Param("days") int days,
                                                                                     @Param("category") String category);

    @Query(value = "select r" +
            " from Restaurant r" +
            " join fetch r.restaurantBzhList" +
            " where r.rtId = :rtId")
    public Restaurant findRestaurantInfoByRtId(@Param("rtId") Long rtId);

//    @Query(value = "select new " +
//            " from Restaurant r" +
//            " join fetch r.restaurantImgList" +
//            " where r.rtId in :rtIds")
//    public List<Restaurant> findRestaurantImg(List<Long> rtIdList);


}
