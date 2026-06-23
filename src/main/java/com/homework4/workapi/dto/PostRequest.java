package com.homework4.workapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostRequest {
    @NotBlank(message = "제목은 필수 입니다.")
    private String title;
    @NotBlank(message = "내용은 필수 입니다.")
    private String content;
}
