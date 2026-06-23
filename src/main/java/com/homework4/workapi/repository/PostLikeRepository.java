package com.homework4.workapi.repository;

import com.homework4.workapi.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByPost_IdAndUser_Id(Long postId, Long userId);
}