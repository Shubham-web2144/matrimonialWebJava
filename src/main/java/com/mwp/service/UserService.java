package com.mwp.service;

import com.mwp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public User createUser(User user);

    public List<User> getAllUsers();
    public List<User> getAllUsersExceptOneUSer(String userId);

    public User getUserById(String userId);

    public User getUserByEmail(String email);

    public void deleteAll();
}
