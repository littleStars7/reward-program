package com.example.reward_program.model;

import java.util.ArrayList;
import java.util.List;

public class CustomerRewards {
    private String customerId;
    private Integer totalPoints;
    private List<MonthlyRewards> monthlyRewardsList = new ArrayList<>();

    public CustomerRewards(String customerId, Integer totalPoints, List<MonthlyRewards> monthlyRewardsList) {
        this.customerId = customerId;
        this.totalPoints = totalPoints;
        this.monthlyRewardsList = monthlyRewardsList;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public List<MonthlyRewards> getMonthlyRewardsList() {
        return monthlyRewardsList;
    }

    public void setMonthlyRewardsList(List<MonthlyRewards> monthlyRewardsList) {
        this.monthlyRewardsList = monthlyRewardsList;
    }
}
