package com.mwp.repo;

import com.mwp.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    public User findByEmail(String email);

    @Query(value = "SELECT u FROM Users u WHERE u.userId != ?1", nativeQuery = true)
    public List<User> findUserByUserIdIsNotEqualTo(String userId);
}
