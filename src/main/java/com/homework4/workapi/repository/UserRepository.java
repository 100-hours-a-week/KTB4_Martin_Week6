package com.homework4.workapi.repository;

import com.homework4.workapi.entity.User;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Repository
public class UserRepository {
    private final Map<Long, User> users = new LinkedHashMap<>();
    private Long sequence = 1L;

    public Long nextId(){
        return sequence++;
    }
}
