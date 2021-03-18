package com.transactionservice.dto.request.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TransactionRequestDto {
    private static final String DIGIT_PATTERN = "^(0|[1-9][0-9]*)$";
    @JsonProperty("source_acc_id")
    @Pattern(regexp = DIGIT_PATTERN, message = "Account payer number must contain only digits")
    private String accountNumberFrom;
    @JsonProperty("dest_acc_id")
    @Pattern(regexp = DIGIT_PATTERN, message = "Account recipient number must contain only digits")
    private String accountNumberTo;
    @JsonProperty("amount")
    @Min(value = 0, message = "Amount must be more than zero")
    private BigDecimal amount;
    @JsonProperty("reason")
    @NotNull(message = "Reason cannot be null")
    private String reason;
}
