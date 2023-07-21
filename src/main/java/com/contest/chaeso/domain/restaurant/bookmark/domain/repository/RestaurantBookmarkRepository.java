package com.contest.chaeso.domain.restaurant.bookmark.domain.repository;

import com.contest.chaeso.domain.restaurant.bookmark.domain.RestaurantBookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantBookmarkRepository extends JpaRepository<RestaurantBookmark, Long> {
}
