package com.homework4.workapi.controller;

import com.homework4.workapi.Service.CommentService;
import com.homework4.workapi.dto.CommentRequest;
import com.homework4.workapi.dto.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public CommentResponse addComment(
            @PathVariable Long postId,
            @RequestParam Long userId,
            @RequestBody CommentRequest commentRequest
    ) {
        return commentService.addComment(commentRequest, postId, userId);
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
            @RequestBody CommentRequest commentRequest
    ) {
        return commentService.updateComment(commentId, commentRequest, postId, userId);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestParam Long userId
    ) {
        commentService.deleteComment(commentId, userId);
    }
}