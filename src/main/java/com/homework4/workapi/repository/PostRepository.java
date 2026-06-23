package com.homework4.workapi.repository;

import com.homework4.workapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUser_Id(Long userId);
}
