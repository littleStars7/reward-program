package com.example.reward_program.service;

import com.example.reward_program.entity.Customer;
import com.example.reward_program.entity.Transaction;
import com.example.reward_program.model.MonthlyRewards;
import com.example.reward_program.repository.CustomerRepository;
import com.example.reward_program.repository.TransactionRepository;
import com.example.reward_program.util.RewardsPointsCalculator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardsServiceImpl implements RewardsService{
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    public RewardsServiceImpl(CustomerRepository customerRepository,
                              TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Map<String, Object> getCustomerRewards(Long customerId) {

        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);

        Map<String, Integer> monthlyPointsMap = new HashMap<>();
        int totalPoints = 0;

        for (Transaction t : transactions) {
            int points = RewardsPointsCalculator.calculatePoints(t.getAmount());
            String month = t.getTransactionDate().getMonth().toString();

            monthlyPointsMap.put(month, monthlyPointsMap.getOrDefault(month, 0) + points);
            totalPoints += points;
        }

        // Convert to list for UI
        List<MonthlyRewards> monthlyRewards = monthlyPointsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> new MonthlyRewards(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("customerId", customerId);
        result.put("monthlyRewards", monthlyRewards);
        result.put("totalPoints", totalPoints);

        return result;
    }

    @Override
    public List<Long> getAllCustomerIds() {
        return customerRepository.findAll()
                .stream()
                .map(Customer::getId)
                .collect(Collectors.toList());
    }
}
