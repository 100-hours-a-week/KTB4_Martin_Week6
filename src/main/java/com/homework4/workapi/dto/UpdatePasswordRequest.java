package com.homework4.workapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdatePasswordRequest {
    @NotBlank(message = "새 비밀번호는 필수 입니다.")
    private String newPassword;
}
