package com.homework4.workapi.dto;

import lombok.Getter;
import com.homework4.workapi.entity.Comment;
import java.time.LocalDateTime;
@Getter
public class CommentResponse {
    private Long id;
    private Long postId;
    private Long userId;
    private String username;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.postId = comment.getPostId();
        this.userId = comment.getUserId();
        this.username = comment.getUsername();
        this.content = comment.getContent();
        this.createTime = comment.getCreateTime();
        this.updateTime = comment.getUpdateTime();
    }
}
