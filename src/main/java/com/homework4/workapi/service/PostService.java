package com.homework4.workapi.service;

import com.homework4.workapi.dto.PostRequest;
import com.homework4.workapi.dto.PostResponse;
import com.homework4.workapi.dto.UpdatePostRequest;
import com.homework4.workapi.entity.Post;
import com.homework4.workapi.entity.PostLike;
import com.homework4.workapi.entity.User;
import com.homework4.workapi.repository.PostLikeRepository;
import com.homework4.workapi.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostLikeRepository postLikeRepository;

    @Transactional
    public PostResponse addPost(Long userId, PostRequest postRequest) {
        User user = userService.findUserById(userId);
        Post post = new Post(user, postRequest.getTitle(), postRequest.getContent());
        Post savedPost = postRepository.save(post);
        return new PostResponse(savedPost);
    }

    @Transactional
    public List<PostResponse> getPosts() {
        return postRepository.findAll().stream()
                .map(PostResponse::new)
                .toList();
    }

    @Transactional
    public PostResponse getPost(Long postId) {
        Post post = findPostById(postId);

        return new PostResponse(post);
    }

    @Transactional
    public PostResponse deletePost(Long postId, Long userId) {
        Post post = findPostById(postId);
        if(!post.isWritten(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "게시글 작성자만 삭제 할 수 있습니다.");
        }
        postRepository.delete(post);
        return new PostResponse(post);
    }

    @Transactional
    public PostResponse updatePost(Long postId, Long userId, UpdatePostRequest postRequest) {
        Post post = findPostById(postId);
        if(!post.isWritten(userId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "게시글 작성자만 수정 가능합니다.");
        }
        post.update(postRequest.getTitle(), postRequest.getContent());
        Post savedPost = postRepository.save(post);
        return new PostResponse(savedPost);
    }

    @Transactional
    public PostResponse likePost(Long postId, Long userId) {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        Optional<PostLike> optionalPostLike =
                postLikeRepository.findByPost_IdAndUser_Id(postId, userId);

        if (optionalPostLike.isEmpty()) {
            PostLike postLike = new PostLike(post, user);
            postLikeRepository.save(postLike);
            post.likeIncrease();
        }

        return new PostResponse(post);
    }

    @Transactional
    public PostResponse unlikePost(Long postId, Long userId) {
        Post post = findPostById(postId);
        userService.findUserById(userId);

        Optional<PostLike> optionalPostLike =
                postLikeRepository.findByPost_IdAndUser_Id(postId, userId);

        if (optionalPostLike.isPresent()) {
            PostLike postLike = optionalPostLike.get();
            postLikeRepository.delete(postLike);
            post.likeDecrease();
        }

        return new PostResponse(post);
    }

    public Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));
    }
}
