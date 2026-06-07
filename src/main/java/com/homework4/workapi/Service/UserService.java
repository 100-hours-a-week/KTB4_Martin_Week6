package com.homework4.workapi.Service;

import com.homework4.workapi.dto.SignupRequest;
import com.homework4.workapi.dto.UserRequest;
import com.homework4.workapi.dto.UserResponse;
import com.homework4.workapi.entity.User;
import com.homework4.workapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponse signup(SignupRequest signupRequest) {
        Long userId = userRepository.nextId();

        User user = new User(userId, signupRequest.getUsername(), signupRequest.getPassword(), signupRequest.getEmail());

        userRepository.getUsers().put(userId, user);

        return new UserResponse(user);
    }

    public UserResponse login(UserRequest loginRequest) {
        for (User user : userRepository.getUsers().values()) {
            if (user.getEmail().equals(loginRequest.getEmail())
                    && user.getPassword().equals(loginRequest.getPassword())) {
                return new UserResponse(user);
            }
        }

        return null;
    }

    public UserResponse deleteUser(Long userId) {
        User user = userRepository.getUsers().get(userId);
        userRepository.getUsers().remove(userId);
        return new UserResponse(user);
    }

    public User findUserById(Long userId) {
        return userRepository.getUsers().get(userId);
    }
}
