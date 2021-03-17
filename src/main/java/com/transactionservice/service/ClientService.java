package com.transactionservice.service;

import com.transactionservice.model.Account;
import com.transactionservice.model.Client;
import java.util.List;

public interface ClientService {
    Client save(Client client);

    Client getById(Long id);

    List<Client> getAll(int page, int size, String sortBy);

    Client getByAccount(Account account);
}
