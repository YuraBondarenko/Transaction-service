package com.transactionservice.repository;

import com.transactionservice.model.Account;
import com.transactionservice.model.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "from Transaction t where t.accountFrom = ?1 and  t.accountTo = ?2")
    List<Transaction> getAllByAccountFromAndAccountFrom(Account accountFrom, Account accountTo);
}
