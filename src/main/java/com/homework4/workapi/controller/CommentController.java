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
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
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
    public CommonResponse<List<CommentResponse>> getComments(@PathVariable Long postId) {
        List<CommentResponse> comments = commentService.getComments(postId);
        return new CommonResponse<>(null,comments);
    }

    @PutMapping("/{commentId}")
    public CommonResponse<CommentResponse> updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestParam Long userId,
            @Valid @RequestBody CommentRequest commentRequest
    ) {
        CommentResponse commentResponse = commentService.updateComment(commentId, commentRequest, postId, userId);
        return new CommonResponse<>("댓글을 수정 했습니다.",commentResponse);
    }

    @DeleteMapping("/{commentId}")
    public CommonResponse<Void> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestParam Long userId
    ) {
        commentService.deleteComment(postId, commentId, userId);
        return new CommonResponse<>("댓글을 삭제 했습니다.", null);
    }
}