package com.homework4.workapi.controller;

import com.homework4.workapi.dto.CommentRequest;
import com.homework4.workapi.dto.CommentResponse;
import com.homework4.workapi.dto.CommonResponse;
import com.homework4.workapi.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public CommonResponse<CommentResponse> addComment(
            @PathVariable Long postId,
            @RequestParam Long userId,
            @Valid @RequestBody CommentRequest commentRequest
    ) {
        CommentResponse commentResponse = commentService.addComment(commentRequest, postId, userId);

        if(commentResponse == null) {
            return new CommonResponse<>("존재하지 않은 사용자 또는 게시글 입니다.", null);
        }
        return new CommonResponse<>("댓글이 작성되었습니다.", commentResponse);
    }

    @GetMapping
    public List<CommentResponse> getComments(@PathVariable Long postId) {
        return commentService.getComments(postId);
    }

    @PutMapping("/{commentId}")
    public CommentResponse updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestParam Long userId,
            @Valid @RequestBody CommentRequest commentRequest
    ) {
        return commentService.updateComment(commentId, commentRequest, postId, userId);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestParam Long userId
    ) {
        commentService.deleteComment(postId, commentId, userId);
    }
}