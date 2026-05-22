package com.example.boardapp.controller;

import com.example.boardapp.dto.PostCreateRequest;
import com.example.boardapp.dto.PostResponse;
import com.example.boardapp.dto.PostUpdateRequest;
import com.example.boardapp.security.LoginUser;
import com.example.boardapp.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponse>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostResponse> create(@Valid @RequestPart("request") PostCreateRequest request,
                                               @RequestPart(value = "files", required = false) List<MultipartFile> files,
                                               @AuthenticationPrincipal LoginUser loginUser) {
        PostResponse response = postService.create(request, files, loginUser.getMember());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> update(@PathVariable Long id,
                                               @Valid @RequestBody PostUpdateRequest request,
                                               @AuthenticationPrincipal LoginUser loginUser) {
        return ResponseEntity.ok(postService.update(id, request, loginUser.getMember()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       @AuthenticationPrincipal LoginUser loginUser) {
        postService.delete(id, loginUser.getMember());
        return ResponseEntity.noContent().build();
    }
}
