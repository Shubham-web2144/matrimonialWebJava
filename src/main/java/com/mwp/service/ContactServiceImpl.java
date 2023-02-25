package com.mwp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mwp.model.Contact;
import com.mwp.repo.ContactRepo;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepo contactRepo;

    @Override
    public String sendMessage(Contact contact) {
        Contact save = contactRepo.save(contact);
        return save != null ? "success" : "error";
    }
    
}
