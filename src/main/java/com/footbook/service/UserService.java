package com.footbook.service;

import com.footbook.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
