package com.transactionservice.service;

import com.transactionservice.model.User;
import java.util.List;

public interface UserService {
    User save(User user);

    User getById(Long id);

    List<User> getAll(int page, int size, String sortBy);
}
