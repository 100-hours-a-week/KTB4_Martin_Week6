package com.homework4.workapi.dto;

import com.homework4.workapi.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private String username;
    private int likeCount;
    private int commentCount;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private List<AttachResponse> attaches;
    private boolean liked;

    public PostResponse(Post post, int commentCount, boolean liked) {
        this.id = post.getId();
        this.userId = post.getUser().getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUser().getUsername();
        this.likeCount = post.getLikeCount();
        this.commentCount = commentCount;
        this.liked =  liked;
        this.updateTime = post.getUpdateTime();
        this.createTime = post.getCreateTime();
        this.attaches = post.getAttaches().stream()
                .map(AttachResponse::new)
                .toList();
    }

}
