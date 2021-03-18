package com.transactionservice.dto.request.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountRequestDto {
    private static final String DIGIT_PATTERN = "^(0|[1-9][0-9]*)$";
    @JsonProperty("account_num")
    @Pattern(regexp = DIGIT_PATTERN, message = "Account number must contain only digits")
    private String accountNumber;
    @JsonProperty("account_type")
    @NotBlank(message = "Account type must be GOLD, SIMPLE or PLATINUM")
    private String type;
    @JsonProperty("balance")
    @Min(value = 0, message = "Balance must be positive")
    private BigDecimal balance;
}
