package com.transactionservice.service.impl;

import com.transactionservice.model.Account;
import com.transactionservice.model.Client;
import com.transactionservice.repository.ClientRepository;
import com.transactionservice.service.AccountService;
import com.transactionservice.service.ClientService;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final AccountService accountService;

    public ClientServiceImpl(ClientRepository clientRepository, AccountService accountService) {
        this.clientRepository = clientRepository;
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public Client save(Client client) {
        if (client.getAccounts() != null) {
            client.getAccounts().forEach(accountService::save);
        }
        return clientRepository.save(client);
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.getOne(id);
    }

    @Override
    public List<Client> getAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return clientRepository.findAll(pageable).getContent();
    }

    @Override
    public Client getByAccount(Account account) {
        return clientRepository.getByAccounts(List.of(account));
    }
}
