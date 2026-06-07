package com.homework4.workapi.entity;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class Comment {
    private Long id;
    private Long userId;
    private Long postId;
    private String username;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Comment(Long id, String username, Long userId, Long postId, String content, LocalDateTime createTime) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = LocalDateTime.now();
        this.username = username;
    }

    public void updateComment(String content) {
        this.updateTime = LocalDateTime.now();
        this.content = content;
    }
}


