package com.transactionservice.service;

import com.transactionservice.model.Account;

public interface AccountService {
    void save(Account account);

    Account getById(Long id);

    Account getByNumber(String accountNumber);
}
