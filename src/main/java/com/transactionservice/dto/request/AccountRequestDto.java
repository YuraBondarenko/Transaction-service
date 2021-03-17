package com.transactionservice.dto.request;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class AccountRequestDto {
    private String accountNumber;
    private BigDecimal balance;
    private String type;
    private Long clientId;
}
