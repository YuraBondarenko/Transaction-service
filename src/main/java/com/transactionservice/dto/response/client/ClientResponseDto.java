package com.transactionservice.dto.response.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ClientResponseDto {
    @JsonProperty("client_id")
    private Long id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("accounts")
    private List<String> accountNumbers;
}
