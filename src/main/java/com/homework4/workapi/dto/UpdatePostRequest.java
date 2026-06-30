package com.homework4.workapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdatePostRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
