package com.transactionservice.dto.request.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.transactionservice.dto.request.account.AccountRequestDto;
import java.util.List;
import lombok.Data;

@Data
public class ClientRequestDto {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("accounts")
    private List<AccountRequestDto> accounts;
}
