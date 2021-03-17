package com.transactionservice.service.impl;

import com.transactionservice.model.Client;
import com.transactionservice.repository.UserRepository;
import com.transactionservice.service.UserService;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Client save(Client client) {
        return userRepository.save(client);
    }

    @Override
    public Client getById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<Client> getAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userRepository.findAll(pageable).getContent();
    }
}
