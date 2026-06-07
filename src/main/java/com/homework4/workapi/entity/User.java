package com.homework4.workapi.entity;

import lombok.Getter;

@Getter
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;

    public User(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void updateUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }


}
