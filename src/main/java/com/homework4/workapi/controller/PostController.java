package com.homework4.workapi.controller;

import com.homework4.workapi.dto.CommonResponse;
import com.homework4.workapi.dto.PostRequest;
import com.homework4.workapi.dto.PostResponse;
import com.homework4.workapi.dto.UpdatePostRequest;
import com.homework4.workapi.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public CommonResponse<PostResponse> addPost(@RequestParam Long userId, @Valid @RequestBody PostRequest postRequest) {
        PostResponse postResponse = postService.addPost(userId, postRequest);
        if(postResponse == null) {
            return new CommonResponse<>("존재하지 않는 사용자 입니다.", null);
        }
        return new CommonResponse<>("게시글을 등록하였습니다.", postResponse);
    }

    @GetMapping
    public List<PostResponse> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{postId}")
    public PostResponse getPost(
            @PathVariable Long postId,
            @RequestParam Long userId
    ) {
        return postService.getPost(postId, userId);
    }
    @PatchMapping("/{postId}")
    public CommonResponse<PostResponse> updatePost(@PathVariable Long postId, @RequestParam Long userId, @RequestBody UpdatePostRequest postRequest) {
        PostResponse postResponse = postService.updatePost(postId, userId, postRequest);
        return new CommonResponse<>("게시글을 수정하였습니다.", postResponse);
    }

    @DeleteMapping("/{postId}")
    public CommonResponse<PostResponse> deletePost(@PathVariable Long postId, @RequestParam Long userId) {
        PostResponse postResponse = postService.deletePost(postId, userId);
        return new CommonResponse<>("삭제 되었습니다.", postResponse);
    }

    @PostMapping("/{postId}/like")
    public CommonResponse<PostResponse> likePost(@PathVariable Long postId, @RequestParam Long userId) {
        PostResponse postResponse = postService.likePost(postId, userId);
        return new CommonResponse<>("좋아요를 눌렀습니다.", postResponse);
    }

    @PostMapping("/{postId}/unlike")
    public CommonResponse<PostResponse> unlikePost(@PathVariable Long postId, @RequestParam Long userId) {
        PostResponse postResponse = postService.unlikePost(postId, userId);
        return new CommonResponse<>("좋아요를 취소하였습니다.", postResponse);
    }
}
