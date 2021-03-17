package com.transactionservice.dto.response.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.transactionservice.dto.response.client.ClientNameResponseDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TransactionResponseDto {
    @JsonProperty("payment_id")
    private Long id;
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;
    @JsonProperty("source_acc_id")
    private String accountNumberTo;
    @JsonProperty("dest_acc_id")
    private String accountNumberFrom;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("status")
    private String status;
    @JsonProperty("payer")
    private ClientNameResponseDto payer;
    @JsonProperty("recipient")
    private ClientNameResponseDto recipient;
}
