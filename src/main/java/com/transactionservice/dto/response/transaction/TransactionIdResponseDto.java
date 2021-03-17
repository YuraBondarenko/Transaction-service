package com.transactionservice.dto.response.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionIdResponseDto {
    @JsonProperty("payment_id")
    private Long id;
    @JsonProperty("status")
    private String status;
}
