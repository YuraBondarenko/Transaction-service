package com.transactionservice.dto.request.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Min;
import lombok.Data;

@Data
public class TransactionInformationRequestDto {
    @JsonProperty("payer_id")
    @Min(value = 0, message = "Payer id must be non negative")
    private Long payerId;
    @JsonProperty("recipient_id")
    @Min(value = 0, message = "Recipient id must be non negative")
    private Long recipientId;
    @JsonProperty("source_acc_id")
    @Min(value = 0, message = "Account payer id must be non negative")
    private Long accountFromId;
    @JsonProperty("dest_acc_id")
    @Min(value = 0, message = "Account recipient id must be non negative")
    private Long accountToId;
}
