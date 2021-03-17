package com.transactionservice.dto.request.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class AccountRequestDto {
    @JsonProperty("account_num")
    private String accountNumber;
    @JsonProperty("account_type")
    private String type;
    @JsonProperty("balance")
    private BigDecimal balance;
}
