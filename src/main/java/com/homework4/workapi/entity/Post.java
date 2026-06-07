package com.homework4.workapi.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Post {
    private String username;
    private Long id;
    private Long userId;
    private int likeCount;
    private String title;
    private String content;
    private String imageUrl;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Post(Long id, Long userId, int likeCount, String username, String title, String content, String imageUrl, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.createTime = createTime;
        this.userId = userId;
        this.likeCount = likeCount;
        this.updateTime = updateTime;
    }

    public void likeIncrease() {
        this.likeCount++;
    }

    public void likeDecrease() {
        if(this.likeCount > 0) {
            this.likeCount--;
        }
    }

    public void update(String title, String content, String imageUrl) {
        this.updateTime = LocalDateTime.now();
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }
}


