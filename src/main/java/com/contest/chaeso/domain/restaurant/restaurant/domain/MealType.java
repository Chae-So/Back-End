package com.contest.chaeso.domain.restaurant.restaurant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.Embeddable;

import static javax.persistence.AccessType.FIELD;


@NoArgsConstructor
@Getter
@Embeddable
public class MealType {

    private int forHere;
    private int toGo;
    private int delivery;

    public MealType(int forHere, int toGo, int delivery) {
        this.forHere = forHere;
        this.toGo = toGo;
        this.delivery = delivery;
    }
}
