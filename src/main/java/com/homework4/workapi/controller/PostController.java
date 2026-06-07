package com.homework4.workapi.controller;

import com.homework4.workapi.Service.PostService;
import com.homework4.workapi.dto.CommonResponse;
import com.homework4.workapi.dto.PostRequest;
import com.homework4.workapi.dto.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public CommonResponse<PostResponse> addPost(@RequestParam Long userId, @RequestBody PostRequest postRequest) {
        PostResponse postResponse = postService.addPost(userId, postRequest);
        return new CommonResponse<>("게시글을 등록하였습니다.", postResponse);
    }

    @GetMapping
    public List<PostResponse> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @PatchMapping("/{postId}")
    public CommonResponse<PostResponse> updatePost(@PathVariable Long postId, @RequestBody PostRequest postRequest) {
        PostResponse postResponse = postService.updatePost(postId, postRequest);
        return new CommonResponse<>("게시글을 수정하였습니다.", postResponse);
    }

    @DeleteMapping("/{postId}")
    public CommonResponse<PostResponse> deletePost(@PathVariable Long postId) {
        PostResponse postResponse = postService.deletePost(postId);
        return new CommonResponse<>("삭제 되었습니다.", postResponse);
    }

    @PostMapping("/{postId}/like")
    public PostResponse likePost(@PathVariable Long postId) {
        return postService.likePost(postId);
    }

    @PostMapping("/{postId}/unlike")
    public PostResponse unlikePost(@PathVariable Long postId) {
        return postService.unlikePost(postId);
    }
}