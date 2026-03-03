package com.example.reward_program.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RewardsPointsCalculator {
    public static int calculatePoints(BigDecimal amount) {
        int points = 0;

        BigDecimal oneHundred = BigDecimal.valueOf(100);
        BigDecimal fifty = BigDecimal.valueOf(50);

        if (amount.compareTo(oneHundred) > 0) {
            BigDecimal overHundred = amount.subtract(oneHundred);
            points += overHundred.multiply(BigDecimal.valueOf(2)).intValue();
            amount = oneHundred;
        }

        if (amount.compareTo(fifty) > 0) {
            BigDecimal overFifty = amount.subtract(fifty);
            points += overFifty.intValue();
        }

        return points;
    }
}
