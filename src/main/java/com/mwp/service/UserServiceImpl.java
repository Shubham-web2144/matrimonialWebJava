package com.mwp.service;

import com.mwp.model.User;
import com.mwp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public User createUser(User user) {
        String userID = UUID.randomUUID().toString();
        user.setUserId(userID);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return userRepo.findById(userId).get();
    }

    @Override
    public void deleteAll() {
        userRepo.deleteAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> getAllUsersExceptOneUSer(String userId) {
        return userRepo.findUserByUserIdIsNotEqualTo(userId);
    }

    
}
