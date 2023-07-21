package com.contest.chaeso.domain.restaurant.restaurant.domain.repository;

import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {



}
