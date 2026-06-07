package com.homework4.workapi.dto;

import lombok.Getter;

@Getter
public class CommonResponse<T> {
    private String message;
    private T data;
    public CommonResponse(String message, T data){
        this.message = message;
        this.data = data;
    }
}
