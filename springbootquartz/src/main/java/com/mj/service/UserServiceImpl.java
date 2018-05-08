package com.mj.service;

import com.mj.entity.User;
import com.mj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        ///////////////////
        return userOptional.orElse(new User());
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
