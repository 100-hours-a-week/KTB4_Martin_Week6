package com.homework4.workapi.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    protected User(){
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void updateUser(String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void updatePassword(String password) {
        this.password = password;
    }


}
