package com.transactionservice.dto.response.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class AccountResponseDto {
    @JsonProperty("account_id")
    private Long id;
    @JsonProperty("account_num")
    private String accountNumber;
    @JsonProperty("account_type")
    private String cardType;
    @JsonProperty("balance")
    private BigDecimal balance;
}
