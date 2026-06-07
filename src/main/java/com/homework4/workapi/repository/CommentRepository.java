package com.homework4.workapi.repository;

import com.homework4.workapi.entity.Comment;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Repository
public class CommentRepository {
    private final Map<Long, Comment> comments = new LinkedHashMap<>();
    private Long sequence = 1L;

    public Long nextId(){
        return sequence++;
    }
}
