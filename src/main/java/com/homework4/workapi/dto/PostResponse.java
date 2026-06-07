package com.homework4.workapi.dto;

import com.homework4.workapi.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private String username;
    private String imageUrl;
    private int likeCount;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;


    public PostResponse(Post post) {
        this.id = post.getId();
        this.userId = post.getUserId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUsername();
        this.imageUrl = post.getImageUrl();
        this.likeCount = post.getLikeCount();
        this.updateTime = post.getUpdateTime();
        this.createTime = post.getCreateTime();
    }
}
