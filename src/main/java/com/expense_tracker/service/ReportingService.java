package com.expense_tracker.service;

import com.expense_tracker.dto.SpendingSummaryDto;
import com.expense_tracker.entity.Transaction;
import com.expense_tracker.entity.TransactionType;
import com.expense_tracker.entity.User;
import com.expense_tracker.repository.TransactionRepository;
import com.expense_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportingService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public ReportingService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public SpendingSummaryDto weeklySummary(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(7);
        List<Transaction> txs = transactionRepository.findByUserAndDateBetween(user, start, end);

        double totalExpense = txs.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .mapToDouble(Transaction::getAmount).sum();

        double totalIncome = txs.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .mapToDouble(Transaction::getAmount).sum();

        Map<String, Double> categoryTotals = txs.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        return new SpendingSummaryDto(totalExpense, totalIncome, categoryTotals);
    }

    public SpendingSummaryDto monthlySummary(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusMonths(1);
        List<Transaction> txs = transactionRepository.findByUserAndDateBetween(user, start, end);

        double totalExpense = txs.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .mapToDouble(Transaction::getAmount).sum();

        double totalIncome = txs.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .mapToDouble(Transaction::getAmount).sum();

        Map<String, Double> categoryTotals = txs.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        return new SpendingSummaryDto(totalExpense, totalIncome, categoryTotals);
    }
}
