package com.example.reward_program.service;

import com.example.reward_program.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RewardsService {
    Page<Transaction> getAllTransactions(int page, int size);

    Map<String, Object> getCustomerRewards(Long customerId);

    List<Long> getAllCustomerIds();
}
