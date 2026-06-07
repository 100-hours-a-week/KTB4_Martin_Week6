package com.homework4.workapi.controller;

import com.homework4.workapi.Service.UserService;
import com.homework4.workapi.dto.CommonResponse;
import com.homework4.workapi.dto.SignupRequest;
import com.homework4.workapi.dto.UserRequest;
import com.homework4.workapi.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public CommonResponse<UserResponse> signup(@RequestBody SignupRequest signupRequest) {
        UserResponse userResponse = userService.signup(signupRequest);
        return new CommonResponse<>("회원가입에 성공하였습니다.", userResponse);
    }

    @PostMapping("/login")
    public CommonResponse<UserResponse> login(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.login(userRequest);
        return new CommonResponse<>("로그인에 성공하였습니다.", userResponse);
    }

    @DeleteMapping("/{userId}")
    public CommonResponse<UserResponse> deleteUser(@PathVariable Long userId) {
        UserResponse userResponse = userService.deleteUser(userId);
        return new CommonResponse<>("회원 탈퇴에 성공하였습니다.", userResponse);
    }
}