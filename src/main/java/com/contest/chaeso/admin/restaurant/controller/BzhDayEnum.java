package com.contest.chaeso.admin.restaurant.controller;

public enum BzhDayEnum {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    private Integer val;

    BzhDayEnum(Integer val) {
        this.val = val;
    }

    public Integer getVal() {
        return val;
    }
}
