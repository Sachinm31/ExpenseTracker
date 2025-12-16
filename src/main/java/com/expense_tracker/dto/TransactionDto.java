package com.expense_tracker.dto;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TransactionDto {
    private Long id;
    private LocalDate date;
    private Double amount;
    private String category;
    private String description;
    private String type;
}
