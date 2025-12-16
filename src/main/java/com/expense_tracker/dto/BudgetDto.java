package com.expense_tracker.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BudgetDto {
    private Long id;
    private Integer month;
    private Integer year;
    private Double amount;
}
