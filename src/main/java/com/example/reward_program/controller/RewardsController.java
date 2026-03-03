package com.example.reward_program.controller;

import com.example.reward_program.dto.ApiResponse;
import com.example.reward_program.entity.Customer;
import com.example.reward_program.entity.Transaction;
import com.example.reward_program.exception.ResourceNotFoundException;
import com.example.reward_program.service.RewardsServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/transactions")
    public ResponseEntity<ApiResponse> getAllTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Transaction> transactions = rewardsService.getAllTransactions(page, size);
        return ResponseEntity.ok(new ApiResponse(transactions, 200));
    }

    // GET monthly and total rewards by customer
    @GetMapping("/{customerId}")
    public ResponseEntity<ApiResponse> getCustomerRewards(@PathVariable Long customerId) {
        // Call service to get rewards data
        Map<String, Object> rewards = rewardsService.getCustomerRewards(customerId);

        // Wrap result in standardized response
        return ResponseEntity.ok(new ApiResponse(rewards, 200));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCustomersRewards() {
        List<Long> customerIds = rewardsService.getAllCustomerIds();

        // Map each ID to their rewards using stream
        List<Object> rewardsList = customerIds.stream()
                .map(rewardsService::getCustomerRewards)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse(rewardsList, 200));
    }
}
