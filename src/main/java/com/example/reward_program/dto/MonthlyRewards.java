package com.example.reward_program.dto;

public class MonthlyRewards {
    private String month;
    private Integer points;

    public MonthlyRewards(String month, Integer points) {
        this.month = month;
        this.points = points;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
