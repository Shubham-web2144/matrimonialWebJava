package com.mwp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mwp.model.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact,Integer> {
    
}
