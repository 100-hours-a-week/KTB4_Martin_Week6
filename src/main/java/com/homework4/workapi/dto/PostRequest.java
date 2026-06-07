package com.homework4.workapi.dto;

import lombok.Getter;

@Getter
public class PostRequest {
    private String title;
    private String content;
    private String imageUrl;
}
