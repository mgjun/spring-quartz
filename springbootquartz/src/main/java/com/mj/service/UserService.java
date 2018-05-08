package com.mj.service;

import com.mj.entity.User;

public interface UserService {

    User findById(Long id);

    void save(User user);
}
