package com.transactionservice.dto.response.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClientIdResponseDto {
    @JsonProperty("client_id")
    private Long id;
}
