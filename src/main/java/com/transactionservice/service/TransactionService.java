package com.transactionservice.service;

import com.transactionservice.model.Transaction;

public interface TransactionService {
    Transaction save(Transaction transaction);

    //List<Transaction> getAllByUser(int page, int size, String sortBy,);
}
