package com.homework4.workapi.service;

import com.homework4.workapi.dto.SignupRequest;
import com.homework4.workapi.dto.UpdatePasswordRequest;
import com.homework4.workapi.dto.UpdateUserRequest;
import com.homework4.workapi.dto.UserRequest;
import com.homework4.workapi.dto.UserResponse;
import com.homework4.workapi.entity.User;
import com.homework4.workapi.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserResponse signup(SignupRequest signupRequest) {
        User user = new User(signupRequest.getUsername(), signupRequest.getEmail(), signupRequest.getPassword());
        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser);
    }

    public UserResponse login(UserRequest loginRequest) {
        User user = userRepository.findByEmail((loginRequest.getEmail())).orElse(null);

        if(user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            return null;
        }

        return new UserResponse(user);
    }


    @Transactional
    public UserResponse updateUser(Long userId, UpdateUserRequest updateUserRequest) {
        User user = findUserById(userId);
        user.updateUsername(updateUserRequest.getUsername());
        return new UserResponse(user);
    }

    @Transactional
    public UserResponse updatePassword(Long userId, UpdatePasswordRequest updatePasswordRequest) {
        User user = findUserById(userId);
        user.updatePassword(updatePasswordRequest.getNewPassword());
        return new UserResponse(user);
    }

    @Transactional
    public UserResponse deleteUser(Long userId) {
        Optional<User> OptionalUser = userRepository.findById(userId);
        if(OptionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다.");
        }

        User user = OptionalUser.get();
        userRepository.delete(user);

        return new UserResponse(user);
    }

    public User findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"사용자를 찾을 수 없습니다.");
        }
        return user.get();
    }
}
