package com.transactionservice.service.impl;

import com.transactionservice.model.Account;
import com.transactionservice.repository.AccountRepository;
import com.transactionservice.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getById(Long id) {
        return accountRepository.getOne(id);
    }

    @Override
    public Account getByNumber(String accountNumber) {
        return accountRepository.getByAccountNumber(accountNumber);
    }
}
