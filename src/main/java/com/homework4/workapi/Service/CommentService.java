package com.homework4.workapi.Service;

import com.homework4.workapi.dto.CommentRequest;
import com.homework4.workapi.dto.CommentResponse;
import com.homework4.workapi.dto.PostResponse;
import com.homework4.workapi.entity.Comment;
import com.homework4.workapi.entity.User;
import com.homework4.workapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    public CommentResponse addComment(CommentRequest commentRequest, Long postId, Long userId) {
        User user = userService.findUserById(userId);
        PostResponse post = postService.getPost(postId);

        Long commentId = commentRepository.nextId();
        LocalDateTime now = LocalDateTime.now();

        Comment comment = new Comment(
                commentId,
                user.getUsername(),
                user.getId(),
                postId,
                commentRequest.getContent(),
                now
        );

        commentRepository.getComments().put(commentId, comment);

        return new  CommentResponse(comment);
    }

    public List<CommentResponse> getComments(Long postId) {
        List<CommentResponse> comments = new ArrayList<>();

        for(Comment comment : commentRepository.getComments().values()){
            if(comment.getPostId().equals(postId)){
                comments.add(new CommentResponse(comment));
            }
        }

        return comments;
    }

    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.getComments().get(commentId);
        commentRepository.getComments().remove(commentId);
    }

    public CommentResponse updateComment(Long commentId, CommentRequest commentRequest, Long postId, Long userId) {
        Comment comment = commentRepository.getComments().get(commentId);
        comment.updateComment(commentRequest.getContent());
        return new  CommentResponse(comment);
    }
}
