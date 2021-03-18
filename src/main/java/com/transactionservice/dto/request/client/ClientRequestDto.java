package com.transactionservice.dto.request.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.transactionservice.dto.request.account.AccountRequestDto;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClientRequestDto {
    @JsonProperty("first_name")
    @NotBlank(message = "First name cannot be empty")
    private String firstName;
    @JsonProperty("last_name")
    @NotBlank(message = "Last name cannot be empty")
    private String lastName;
    @JsonProperty("accounts")
    private @Valid List<AccountRequestDto> accounts;
}
