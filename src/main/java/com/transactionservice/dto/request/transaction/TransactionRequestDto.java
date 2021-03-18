package com.transactionservice.dto.request.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionRequestDto {
    @JsonProperty("source_acc_id")
    private String accountFrom;
    @JsonProperty("dest_acc_id")
    private String accountTo;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("reason")
    private String reason;
}
