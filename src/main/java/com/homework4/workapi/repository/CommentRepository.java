package com.homework4.workapi.repository;

import com.homework4.workapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost_Id(Long postId);
    int countByPost_Id(Long postId);
}
