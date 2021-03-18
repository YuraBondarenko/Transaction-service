package com.transactionservice.repository;

import com.transactionservice.model.Account;
import com.transactionservice.model.Client;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query(value = "from Client c where c.accounts = ?1")
    Client getByAccounts(List<Account> account);
}
