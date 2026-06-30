package com.homework4.workapi.service;

import com.homework4.workapi.dto.CommentRequest;
import com.homework4.workapi.dto.CommentResponse;
import com.homework4.workapi.entity.Comment;
import com.homework4.workapi.entity.Post;
import com.homework4.workapi.entity.User;
import com.homework4.workapi.repository.CommentRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @Transactional
    public CommentResponse addComment(CommentRequest commentRequest, Long postId, Long userId) {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        Comment comment = new Comment(user, post, commentRequest.getContent());

        Comment savedComment = commentRepository.save(comment);
        return new CommentResponse(savedComment);
    }

    public List<CommentResponse> getComments(Long postId) {
        postService.findPostById(postId);

        List<Comment> comments = commentRepository.findByPost_Id(postId);
        List<CommentResponse> responses = new ArrayList<>();

        for (Comment comment : comments) {
            responses.add(new CommentResponse(comment));
        }

        return responses;
    }
    @Transactional
    public void deleteComment(Long postId, Long commentId, Long userId) {
        Comment comment = findCommentById(commentId);
        if (!comment.getPost().getId().equals(postId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다.");
        }
        if(!comment.isWritten(userId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "댓글 작성자만 삭제 할 수 있습니다.");
        }
        commentRepository.delete(comment);
    }

    @Transactional
    public CommentResponse updateComment(Long commentId, CommentRequest commentRequest, Long postId, Long userId) {
        Comment comment = findCommentById(commentId);

        if(!comment.getPost().getId().equals(postId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.");
        }
        if(!comment.isWritten(userId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "댓글 작성자만 수정 할 수 있습니다.");
        }

        comment.updateComment(commentRequest.getContent());
        return new  CommentResponse(comment);
    }

    public Comment findCommentById(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글이 없습니다.");
        }
        return comment.get();
    }
}
