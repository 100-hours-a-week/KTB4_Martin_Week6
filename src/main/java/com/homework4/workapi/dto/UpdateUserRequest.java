package com.homework4.workapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateUserRequest {
    @NotBlank(message = "사용자 이름은 필수 입니다.")
    private String username;
}
