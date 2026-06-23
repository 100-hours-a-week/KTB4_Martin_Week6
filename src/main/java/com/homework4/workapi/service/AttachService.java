package com.homework4.workapi.service;

import com.homework4.workapi.dto.AttachRequest;
import com.homework4.workapi.dto.AttachResponse;
import com.homework4.workapi.entity.Attach;
import com.homework4.workapi.entity.Post;
import com.homework4.workapi.repository.AttachRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttachService {
    @Autowired
    private AttachRepository attachRepository;

    @Autowired
    private PostService postService;

    @Transactional
    public AttachResponse addAttach(Long postId, Long userId, AttachRequest attachRequest) {
        Post post = postService.findPostById(postId);

        if(!post.isWritten(userId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "게시글 작성자만 첨부파일을 첨부 할 수 있습니다.");
        }
        Attach attach = new Attach(post, attachRequest.getAttachUrl());

        Attach savedAttach = attachRepository.save(attach);

        return new AttachResponse(savedAttach);
    }

    @Transactional
    public List<AttachResponse> getAttaches(Long postId) {
        postService.findPostById(postId);

        List<Attach> attaches = attachRepository.findByPost_Id(postId);
        List<AttachResponse> responses = new ArrayList<>();

        for (Attach attach : attaches) {
            responses.add(new AttachResponse(attach));
        }

        return responses;
    }

    @Transactional
    public AttachResponse deleteAttach(Long attachId,  Long userId) {
        Optional<Attach> Attach = attachRepository.findById(attachId);

        if (Attach.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "첨부파일을 찾을 수 없습니다.");
        }


        Attach attach = Attach.get();
        Post post = attach.getPost();
        if(!post.isWritten(userId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "게시글 작성자만 삭제 할 수 있습니다.");
        }
        attachRepository.delete(attach);

        return new AttachResponse(attach);
    }
}