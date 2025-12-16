package com.expense_tracker.dto;

import lombok.*;
import java.util.Map;

@Getter @AllArgsConstructor
public class SpendingSummaryDto {
    private double totalExpense;
    private double totalIncome;
    private Map<String, Double> categoryTotals;
}
