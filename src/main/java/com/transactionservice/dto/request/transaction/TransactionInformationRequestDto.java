package com.transactionservice.dto.request.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionInformationRequestDto {
    @JsonProperty("payer_id")
    private Long payerId;
    @JsonProperty("recipient_id")
    private Long recipientId;
    @JsonProperty("source_acc_id")
    private Long accountFromId;
    @JsonProperty("dest_acc_id")
    private Long accountToId;
}
