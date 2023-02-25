package com.mwp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String userId;

    private String username;
    private String email;

    
    private String img;
    private String gender;
    private int age;
    private String lfr;
    private String password;

    

    public User(String userId, String username, String email, String img, String gender, int age, String lfr, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.img = img;
        this.gender = gender;
        this.age = age;
        this.lfr = lfr;
        this.password = password;
    }

    public User() {
    }

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLfr() {
        return lfr;
    }

    public void setLfr(String lfr) {
        this.lfr = lfr;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
