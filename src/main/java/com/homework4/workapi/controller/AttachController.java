package com.homework4.workapi.controller;

import com.homework4.workapi.dto.AttachRequest;
import com.homework4.workapi.dto.AttachResponse;
import com.homework4.workapi.dto.CommonResponse;
import com.homework4.workapi.service.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class AttachController {
    @Autowired
    private AttachService attachService;

    @PostMapping("/posts/{postId}/attachments")
    public CommonResponse<AttachResponse> addAttach(
            @PathVariable Long postId,
            @RequestParam Long userId,
            @RequestBody AttachRequest attachRequest) {
        AttachResponse attachResponse = attachService.addAttach(postId, userId, attachRequest);
        return new CommonResponse<>("첨부파일이 등록되었습니다.", attachResponse);
    }

    @GetMapping("/posts/{postId}/attachments")
    public CommonResponse<List<AttachResponse>> getAttaches(
            @PathVariable Long postId
    ) {
        List<AttachResponse> attaches = attachService.getAttaches(postId);
        return new CommonResponse<>(null, attaches);
    }

    @DeleteMapping("/attachments/{attachId}")
    public CommonResponse<AttachResponse> deleteAttach(
            @PathVariable Long attachId,
            @RequestParam Long userId
    ) {
        AttachResponse attachResponse = attachService.deleteAttach(attachId, userId);
        return new CommonResponse<>("첨부파일이 삭제되었습니다.", attachResponse);
    }
}
