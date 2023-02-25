package com.mwp.service;

import org.springframework.stereotype.Service;

import com.mwp.model.Contact;

@Service
public interface ContactService {
    
    public String sendMessage(Contact contact);

}
