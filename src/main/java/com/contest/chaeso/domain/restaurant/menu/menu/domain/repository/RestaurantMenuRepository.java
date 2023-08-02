package com.contest.chaeso.domain.restaurant.menu.menu.domain.repository;

import com.contest.chaeso.domain.restaurant.menu.menu.domain.RestaurantMenu;
import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantMenuRepository extends JpaRepository<RestaurantMenu, Long> {

    @Query(value = "select rm" +
            " from RestaurantMenu rm" +
            " join fetch rm.restaurantMenuImgList" +
            " where rm.restaurant.rtId = :rtId")
    public List<RestaurantMenu> findRestaurantMenuByRestaurantId(@Param("rtId") Long rtId);

}
