package com.transactionservice.dto.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class AccountResponseDto {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private String cardType;
    private Long clientId;
}
