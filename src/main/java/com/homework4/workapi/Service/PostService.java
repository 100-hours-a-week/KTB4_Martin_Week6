package com.homework4.workapi.Service;

import com.homework4.workapi.dto.PostRequest;
import com.homework4.workapi.dto.PostResponse;
import com.homework4.workapi.entity.Post;
import com.homework4.workapi.entity.User;
import com.homework4.workapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;

    public PostResponse addPost(Long userId, PostRequest postRequest) {
        User user = userService.findUserById(userId);
        Long postId = postRepository.nextId();
        LocalDateTime now = LocalDateTime.now();
        Post post = new Post(postId, user.getId(), 0, user.getUsername(), postRequest.getTitle(),
                postRequest.getContent(),
                postRequest.getImageUrl(),
                now,
                now);

        postRepository.getPosts().put(postId, post);

        return new PostResponse(post);

    }

    public List<PostResponse> getPosts() {
        List<PostResponse> postResponses = new ArrayList<>();

        for(Post post : postRepository.getPosts().values()){
            postResponses.add(new PostResponse(post));
        }

        return postResponses;
    }

    public PostResponse getPost(Long postId) {
        Post post = postRepository.getPosts().get(postId);
        return new PostResponse(post);
    }

    public PostResponse deletePost(Long postId) {
        Post post = postRepository.getPosts().get(postId);
        postRepository.getPosts().remove(postId);
        return new PostResponse(post);
    }

    public PostResponse updatePost(Long postId, PostRequest postRequest) {
        Post post = postRepository.getPosts().get(postId);

        post.update(postRequest.getTitle(), postRequest.getContent(), postRequest.getImageUrl());
        return new PostResponse(post);
    }

    public PostResponse likePost(Long postId) {
        Post post = postRepository.getPosts().get(postId);
        post.likeIncrease();
        return new PostResponse(post);
    }

    public PostResponse unlikePost(Long postId) {
        Post post = postRepository.getPosts().get(postId);
        post.likeDecrease();
        return new PostResponse(post);
    }
}
