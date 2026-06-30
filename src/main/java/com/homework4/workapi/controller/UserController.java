package com.homework4.workapi.controller;

import com.homework4.workapi.dto.CommonResponse;
import com.homework4.workapi.dto.SignupRequest;
import com.homework4.workapi.dto.UpdatePasswordRequest;
import com.homework4.workapi.dto.UpdateUserRequest;
import com.homework4.workapi.dto.UserRequest;
import com.homework4.workapi.dto.UserResponse;
import com.homework4.workapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public CommonResponse<UserResponse> signup(@Valid @RequestBody SignupRequest signupRequest) {
        UserResponse userResponse = userService.signup(signupRequest);
        return new CommonResponse<>("회원가입에 성공하였습니다.", userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<UserResponse>> login(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.login(userRequest);

        if(userResponse == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CommonResponse<>("이메일이나 비밀번호가 일치하지 않습니다.", null));
        }
        return ResponseEntity.ok(new CommonResponse<>("로그인에 성공하였습니다.", userResponse));
    }


    @PatchMapping("/{userId}")
    public CommonResponse<UserResponse> updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateUserRequest updateUserRequest
    ) {
        UserResponse userResponse = userService.updateUser(userId, updateUserRequest);
        return new CommonResponse<>("회원정보가 수정되었습니다.", userResponse);
    }

    @PatchMapping("/{userId}/password")
    public CommonResponse<UserResponse> updatePassword(
            @PathVariable Long userId,
            @Valid @RequestBody UpdatePasswordRequest updatePasswordRequest
    ) {
        UserResponse userResponse = userService.updatePassword(userId, updatePasswordRequest);
        return new CommonResponse<>("비밀번호가 수정되었습니다.", userResponse);
    }

    @DeleteMapping("/{userId}")
    public CommonResponse<UserResponse> deleteUser(@PathVariable Long userId) {
        UserResponse userResponse = userService.deleteUser(userId);
        return new CommonResponse<>("회원 탈퇴에 성공하였습니다.", userResponse);
    }
}