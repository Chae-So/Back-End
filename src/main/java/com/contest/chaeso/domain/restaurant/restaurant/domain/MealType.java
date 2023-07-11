package com.contest.chaeso.domain.restaurant.restaurant.domain;

import javax.persistence.Embeddable;

@Embeddable
public class MealType {
    private int forHere;
    private int toGo;
    private int delivery;

}
