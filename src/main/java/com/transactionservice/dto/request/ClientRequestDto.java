package com.transactionservice.dto.request;

import com.transactionservice.model.Account;
import java.util.List;
import lombok.Data;

@Data
public class ClientRequestDto {
    private String firstName;
    private String lastName;
    private List<Account> accounts;
}
