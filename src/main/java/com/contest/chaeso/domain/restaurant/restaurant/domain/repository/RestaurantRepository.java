package com.contest.chaeso.domain.restaurant.restaurant.domain.repository;

import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.RestaurantMainInfoResInterface;
import com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info.RestaurantImgListDto;
import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // 메인화면 리스트 가져오기(간략정보)
    @Query(value = "select distinct r.rt_id as rtId, r.Name as name, r.category as category, bzh.days as days, r.corp_lon as corpLon, r.corp_lat as corpLat, bzh.end_time as endTime, rr.avg_score as avgScore, rb.user_id as bookmark, ri.rt_img_link as imgLink, ri.flag as flag" +
            " FROM restaurant r" +
            " left join (select rb.rt_id, rb.user_id from restaurant_bookmark rb where rb.user_id = :userId) as rb on r.rt_id = rb.rt_id" +
            " left join (select rt_id, avg(score) as avg_score from restaurant_review  group by rt_id) rr on r.rt_id = rr.rt_id" +
            " left join (select bzh.rt_id, bzh.end_time, bzh.days from restaurant_bzh bzh where bzh.days = :days) bzh  on r.rt_id = bzh.rt_id" +
            " left join restaurant_img ri on r.rt_id = ri.rt_id" +
            " where ri.flag = :flag" +
            " order by avg_score desc", nativeQuery = true)
    public List<RestaurantMainInfoResInterface> findRestaurantList(@Param("userId") Long userId,
                                                                   @Param("days") int days,
                                                                   @Param("flag") int flag);


    // 메인화면 리스트(간략정보) 현 위치 500m 내 가져오기
    @Query(value = "select distinct r.rt_id as rtId, r.Name as name, r.category as category, bzh.days as days, r.corp_lon as corpLon, r.corp_lat as corpLat, bzh.end_time as endTime, rr.avg_score as avgScore, rb.user_id as bookmark, ri.rt_img_link as imgLink, ri.flag as flag" +
            " FROM restaurant r" +
            " left join (select rb.rt_id, rb.user_id from restaurant_bookmark rb where rb.user_id = :userId) as rb on r.rt_id = rb.rt_id" +
            " left join (select rt_id, avg(score) as avg_score from restaurant_review  group by rt_id) rr on r.rt_id = rr.rt_id" +
            " left join (select bzh.rt_id, bzh.end_time, bzh.days from restaurant_bzh bzh where bzh.days = :days) bzh  on r.rt_id = bzh.rt_id" +
            " left join restaurant_img ri on r.rt_id = ri.rt_id" +
            " where ri.flag = :flag and ST_DISTANCE_SPHERE(point(:myLon, :myLat), point(r.corp_lon , r.corp_lat)) <= :range" +
            " order by avg_score desc", nativeQuery = true)
    public List<RestaurantMainInfoResInterface> findRestaurantListByMyPosition(@Param("userId") Long userId,
                                                                               @Param("days") int days,
                                                                               @Param("flag") int flag,
                                                                               @Param("myLon") BigDecimal myLon,
                                                                               @Param("myLat") BigDecimal mtLat,
                                                                               @Param("range") int range);


    // category 메인화면 리스트 가져오기(간략정보)
    @Query(value = "select distinct r.rt_id as rtId, r.Name as name, r.category as category, bzh.days as days, r.corp_lon as corpLon, r.corp_lat as corpLat, bzh.end_time as endTime, rr.avg_score as avgScore, rb.user_id as bookmark, ri.rt_img_link as imgLink, ri.flag as flag" +
            " FROM restaurant r" +
            " left join (select rb.rt_id, rb.user_id from restaurant_bookmark rb where rb.user_id = :userId) as rb on r.rt_id = rb.rt_id" +
            " left join (select rt_id, avg(score) as avg_score from restaurant_review  group by rt_id) rr on r.rt_id = rr.rt_id" +
            " left join (select bzh.rt_id, bzh.end_time, bzh.days from restaurant_bzh bzh where bzh.days = :days) bzh on r.rt_id = bzh.rt_id" +
            " left join restaurant_img ri on r.rt_id = ri.rt_id" +
            " where ri.flag = :flag and r.category = :category" +
            " order by avg_score desc", nativeQuery = true)
    public List<RestaurantMainInfoResInterface> findRestaurantByCategory(@Param("userId") Long userId,
                                                                         @Param("days") int days,
                                                                         @Param("category") String category,
                                                                         @Param("flag") int flag);


    // category 메인화면 리스트 현위치 500m 내 가져오기(간략정보)
    @Query(value = "select distinct r.rt_id as rtId, r.Name as name, r.category as category, bzh.days as days, r.corp_lon as corpLon, r.corp_lat as corpLat, bzh.end_time as endTime, rr.avg_score as avgScore, rb.user_id as bookmark, ri.rt_img_link as imgLink, ri.flag as flag" +
            " FROM restaurant r" +
            " left join (select rb.rt_id, rb.user_id from restaurant_bookmark rb where rb.user_id = :userId) as rb on r.rt_id = rb.rt_id" +
            " left join (select rt_id, avg(score) as avg_score from restaurant_review  group by rt_id) rr on r.rt_id = rr.rt_id" +
            " left join (select bzh.rt_id, bzh.end_time, bzh.days from restaurant_bzh bzh where bzh.days = :days) bzh on r.rt_id = bzh.rt_id" +
            " left join restaurant_img ri on r.rt_id = ri.rt_id" +
            " where ri.flag = :flag and r.category = :category and ST_DISTANCE_SPHERE(point(:myLon, :myLat), point(r.corp_lon , r.corp_lat)) <= :range" +
            " order by avg_score desc", nativeQuery = true)
    public List<RestaurantMainInfoResInterface> findRestaurantByCategoryAndMyPosition(@Param("userId") Long userId,
                                                                                      @Param("days") int days,
                                                                                      @Param("category") String category,
                                                                                      @Param("flag") int flag,
                                                                                      @Param("myLon") BigDecimal myLon,
                                                                                      @Param("myLat") BigDecimal mtLat,
                                                                                      @Param("range") int range);

    // 영업시간들 가져오기(info)
    @Query(value = "select r" +
            " from Restaurant r" +
            " join fetch r.restaurantBzhList" +
            " where r.rtId = :rtId")
    public Optional<Restaurant> findRestaurantBzhByRtId(@Param("rtId") Long rtId);


    // 메뉴들 가져오기(menu)
    @Query("select r" +
            " from Restaurant r" +
            " join fetch r.restaurantMenuList rm" +
            " where r.rtId = :rtId")
    public Optional<Restaurant> findRestaurantMenuByRtId(@Param("rtId") Long rtId);


    // 레스토랑 메인 이미지
    @Query("select new com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.info.RestaurantImgListDto(ri.rtImgLink)" +
            " from Restaurant r" +
            " join r.restaurantImgList ri" +
            " where r.rtId = :rtId")
    public List<RestaurantImgListDto> getRestaurantImgByRtId(@Param("rtId") Long rtId);


    // 마에페이지 북마크  리스트 가져오기(간략정보)
    @Query(value = "select distinct r.rt_id as rtId, r.Name as name, r.category as category, bzh.days as days, r.corp_lon as corpLon, r.corp_lat as corpLat, bzh.end_time as endTime, rr.avg_score as avgScore, rbb.user_id as bookmark, ri.rt_img_link as imgLink, ri.flag as flag" +
            " FROM restaurant r" +
            " left join (select rb.rt_id, rb.user_id from restaurant_bookmark rb where rb.user_id = :userId) as rbb on r.rt_id = rbb.rt_id" +
            " left join (select rt_id, avg(score) as avg_score from restaurant_review  group by rt_id) rr on r.rt_id = rr.rt_id" +
            " left join (select bzh.rt_id, bzh.end_time, bzh.days from restaurant_bzh bzh where bzh.days = :days) bzh  on r.rt_id = bzh.rt_id" +
            " left join restaurant_img ri on r.rt_id = ri.rt_id" +
            " where ri.flag = :flag and rbb.user_id = :userId" +
            " order by avg_score desc", nativeQuery = true)
    public List<RestaurantMainInfoResInterface> findMyBookmarkRestaurantList(@Param("userId") Long userId,
                                                                   @Param("days") int days,
                                                                   @Param("flag") int flag);


}
