package com.transactionservice.service;

import com.transactionservice.model.Account;
import com.transactionservice.model.Transaction;
import java.util.List;

public interface TransactionService {
    Transaction save(Transaction transaction);

    List<Transaction> getByAccounts(Account accountFrom, Account accountTo);
}
