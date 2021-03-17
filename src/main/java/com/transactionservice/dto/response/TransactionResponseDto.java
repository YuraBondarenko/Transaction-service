package com.transactionservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TransactionResponseDto {
    private Long id;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String accountNumberTo;
    private String accountNumberFrom;
    private String reason;
}
