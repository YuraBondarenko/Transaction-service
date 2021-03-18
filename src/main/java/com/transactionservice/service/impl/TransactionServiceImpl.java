package com.transactionservice.service.impl;

import com.transactionservice.model.Account;
import com.transactionservice.model.Transaction;
import com.transactionservice.repository.TransactionRepository;
import com.transactionservice.service.AccountService;
import com.transactionservice.service.TransactionService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public Transaction save(Transaction transaction) {
        transaction.setTimestamp(LocalDateTime.now());
        Account accountTo = transaction.getAccountTo();
        Account accountFrom = transaction.getAccountFrom();
        try {
            accountFrom.setBalance(accountFrom.getBalance().subtract(transaction.getAmount()));
            accountTo.setBalance(accountTo.getBalance().add(transaction.getAmount()));
            accountService.save(accountFrom);
            accountService.save(accountTo);
            transaction.setStatus(Transaction.Status.OK);
        } catch (ArithmeticException e) {
            transaction.setStatus(Transaction.Status.ERROR);
        }
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getByAccounts(Account accountFrom, Account accountTo) {
        return transactionRepository.getAllByAccountFromAndAccountFrom(accountFrom, accountTo);
    }
}
