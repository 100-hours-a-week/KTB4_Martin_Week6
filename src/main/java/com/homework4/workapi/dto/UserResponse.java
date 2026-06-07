package com.homework4.workapi.dto;

import com.homework4.workapi.entity.User;
import lombok.Getter;

@Getter
public class UserResponse {
    private Long id;
    private String username;
    private String email;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
