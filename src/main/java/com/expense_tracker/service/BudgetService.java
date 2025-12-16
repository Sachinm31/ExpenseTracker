package com.expense_tracker.service;

import com.expense_tracker.entity.Budget;
import com.expense_tracker.entity.User;
import com.expense_tracker.repository.BudgetRepository;
import com.expense_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;

    public BudgetService(BudgetRepository budgetRepository, UserRepository userRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
    }

    public Budget upsert(String email, Budget budget) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return budgetRepository.findByUserAndMonthAndYear(user, budget.getMonth(), budget.getYear())
                .map(existing -> {
                    existing.setAmount(budget.getAmount());
                    return budgetRepository.save(existing);
                })
                .orElseGet(() -> {
                    budget.setUser(user);
                    return budgetRepository.save(budget);
                });
    }
}
