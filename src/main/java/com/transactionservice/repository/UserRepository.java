package com.transactionservice.repository;

import com.transactionservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Client, Long> {
}
