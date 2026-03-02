package com.example.reward_program.controller;

import com.example.reward_program.entity.Transaction;
import com.example.reward_program.service.RewardsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rewards")
public class RewardsController {
    private final RewardsServiceImpl rewardsService;

    public RewardsController(RewardsServiceImpl rewardsService) {
        this.rewardsService = rewardsService;
    }

    // GET all transactions
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = rewardsService.getAllTransactions();
        if (transactions.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 if no data
        }
        return ResponseEntity.ok(transactions); // 200 with data
    }

    // GET monthly and total rewards by customer
    @GetMapping("/{customerId}")
    public ResponseEntity<Map<String, Object>> getCustomerRewards(@PathVariable Long customerId) {
        Map<String, Object> rewards = rewardsService.getCustomerRewards(customerId);

        if (rewards == null || rewards.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Customer not found or no transactions"));
        }

        return ResponseEntity.ok(rewards); // 200 OK
    }

    @GetMapping("/rewards")
    public ResponseEntity<List<Map<String, Object>>> getAllCustomersRewards() {
        List<Long> customerIds = rewardsService.getAllCustomerIds();
        if (customerIds.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<Map<String, Object>> rewardsList = customerIds.stream()
                .map(rewardsService::getCustomerRewards)
                .collect(Collectors.toList());

        return ResponseEntity.ok(rewardsList);
    }
}
