package com.contest.chaeso.domain.restaurant.bzhour.domain;

import com.contest.chaeso.domain.restaurant.restaurant.domain.Restaurant;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static javax.persistence.FetchType.LAZY;

/**
 * 회의하기
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RestaurantBzh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rt_bzh_id")
    private Long rtBzhId;
    @NotNull
    private Integer days;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime breakTime;
    private LocalTime lastOrderTime;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "rt_id")
    private Restaurant restaurant;

    @Builder
    private RestaurantBzh(Integer days, LocalTime startTime, LocalTime endTime, LocalTime breakTime, LocalTime lastOrderTime, Restaurant restaurant) {
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
        this.breakTime = breakTime;
        this.lastOrderTime = lastOrderTime;
        this.restaurant = restaurant;
    }

    /**
     * 생성 메서드
     * @param days
     * @param startTime
     * @param endTime
     * @param breakTime
     * @param lastOrderTime
     * @return
     */
    public static RestaurantBzh createRestaurantBzh(Integer days, LocalTime startTime, LocalTime endTime, LocalTime breakTime, LocalTime lastOrderTime, Restaurant restaurant) {
        return RestaurantBzh.builder()
                .days(days)
                .startTime(startTime)
                .endTime(endTime)
                .breakTime(breakTime)
                .lastOrderTime(lastOrderTime)
                .restaurant(restaurant)
                .build();
    }

    /**
     * restaurant cascade 생성
     * @param days
     * @param startTime
     * @param endTime
     * @param breakTime
     * @param lastOrderTime
     * @return
     */
    public static RestaurantBzh createRestaurantBzhWithCascade(Integer days, LocalTime startTime, LocalTime endTime, LocalTime breakTime, LocalTime lastOrderTime) {
        return RestaurantBzh.builder()
                .days(days)
                .startTime(startTime)
                .endTime(endTime)
                .breakTime(breakTime)
                .lastOrderTime(lastOrderTime)
                .build();
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
