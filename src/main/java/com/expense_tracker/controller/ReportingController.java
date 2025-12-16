package com.expense_tracker.controller;

import com.expense_tracker.dto.SpendingSummaryDto;
import com.expense_tracker.entity.Transaction;
import com.expense_tracker.entity.TransactionType;
import com.expense_tracker.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportingController {

    private final TransactionRepository transactionRepository;

    public ReportingController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/weekly")
    public SpendingSummaryDto getWeeklySummary() {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(7);

        List<Transaction> transactions = transactionRepository.findAll();
        List<Transaction> filtered = transactions.stream()
                .filter(t -> !t.getDate().isBefore(start) && !t.getDate().isAfter(end))
                .collect(Collectors.toList());

        double expense = filtered.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .mapToDouble(Transaction::getAmount).sum();

        double income = filtered.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .mapToDouble(Transaction::getAmount).sum();

        Map<String, Double> categories = filtered.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        return new SpendingSummaryDto(expense, income, categories);
    }
}
