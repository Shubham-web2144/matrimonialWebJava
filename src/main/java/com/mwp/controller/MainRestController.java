package com.mwp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mwp.model.User;
import com.mwp.service.UserService;

@RestController
public class MainRestController {

    @Autowired
    UserService userService;

    @DeleteMapping("/clear")
    public String deleteAllData() {
        userService.deleteAll();
        return "success";
    }

    @GetMapping("/getUserByEmail/{email}")
    public User getUser(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }

}
