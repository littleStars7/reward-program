package com.example.reward_program.service;

import com.example.reward_program.entity.Customer;
import com.example.reward_program.entity.Transaction;
import com.example.reward_program.dto.MonthlyRewards;
import com.example.reward_program.exception.ResourceNotFoundException;
import com.example.reward_program.repository.CustomerRepository;
import com.example.reward_program.repository.TransactionRepository;
import com.example.reward_program.util.RewardsPointsCalculator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardsServiceImpl implements RewardsService{
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    private final RewardsPointsCalculator calculator;


    public RewardsServiceImpl(CustomerRepository customerRepository,
                              TransactionRepository transactionRepository, RewardsPointsCalculator calculator) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
        this.calculator = calculator;
    }

    @Override
    public Page<Transaction> getAllTransactions(int page, int size) {
        return transactionRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Map<String, Object> getCustomerRewards(Long customerId) {

        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer not found: " + customerId);
        }

        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);

        Map<String, Integer> monthlyPoints = transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getTransactionDate().getMonth().toString(),
                        Collectors.summingInt(t -> calculator.calculatePoints(t.getAmount()))
                ));

        int totalPoints = monthlyPoints.values().stream().mapToInt(Integer::intValue).sum();

        List<MonthlyRewards> monthlyRewards = monthlyPoints.entrySet().stream()
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
