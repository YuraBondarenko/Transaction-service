package com.transactionservice.dto.request;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionRequestDto {
    private BigDecimal amount;
    private String accountTo;
    private String accountFrom;
    private String reason;
}
