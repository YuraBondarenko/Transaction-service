package com.transactionservice.repository;

import com.transactionservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account getByAccountNumber(String accountNumber);

    Account deleteByAccountNumber(String accountNumber);
}
