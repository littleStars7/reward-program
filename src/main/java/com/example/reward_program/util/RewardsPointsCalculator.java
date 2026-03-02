package com.example.reward_program.util;

public class RewardsPointsCalculator {
    public static int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (int)((amount - 100) * 2);
            amount = 100;
        }
        if (amount > 50) {
            points += (int)(amount - 50);
        }
        return points;
    }
}
