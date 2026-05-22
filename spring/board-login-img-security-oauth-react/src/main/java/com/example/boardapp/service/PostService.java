package com.example.boardapp.service;

import com.example.boardapp.domain.Member;
import com.example.boardapp.domain.Post;
import com.example.boardapp.domain.PostImage;
import com.example.boardapp.dto.PostCreateRequest;
import com.example.boardapp.dto.PostResponse;
import com.example.boardapp.dto.PostUpdateRequest;
import com.example.boardapp.exception.ForbiddenException;
import com.example.boardapp.exception.NotFoundException;
import com.example.boardapp.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final FileStorageService fileStorageService;

    public List<PostResponse> findAll() {
        return postRepository.findAll().stream()
                .map(PostResponse::from)
                .toList();
    }

    public PostResponse findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        return PostResponse.from(post);
    }

    @Transactional
    public PostResponse create(PostCreateRequest request, List<MultipartFile> files, Member loginMember) {

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .member(loginMember)
                .build();

        if (files != null && !files.isEmpty()) {
            List<FileStorageService.StoredFile> storedFiles = fileStorageService.storeAll(files);

            for (FileStorageService.StoredFile sf : storedFiles) {
                PostImage image = PostImage.builder()
                        .originalName(sf.originalName())
                        .storedName(sf.storedName())
                        .thumbnailName(sf.thumbnailName())
                        .dateFolder(sf.dateFolder())
                        .post(post)
                        .build();
                post.addImage(image);
            }
        }

        Post saved = postRepository.save(post);
        return PostResponse.from(saved);
    }

    @Transactional
    public PostResponse update(Long id, PostUpdateRequest request, Member loginMember) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        checkOwnership(post, loginMember);

        post.update(request.getTitle(), request.getContent());

        return PostResponse.from(post);
    }

    @Transactional
    public void delete(Long id, Member loginMember) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        checkOwnership(post, loginMember);

        postRepository.delete(post);
    }

    private void checkOwnership(Post post, Member loginMember) {
        if (!post.getMember().getId().equals(loginMember.getId()))
            throw new ForbiddenException("본인이 작성한 글만 수정/삭제할 수 있습니다.");
    }

}
