package com.example.secondproject.service;

import com.example.secondproject.dto.CommentDto;
import com.example.secondproject.entity.Article;
import com.example.secondproject.entity.Comment;
import com.example.secondproject.repository.ArticleRepository;
import com.example.secondproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<CommentDto> comments(Long articleId){
        return commentRepository.findByArticleId(articleId)
                .stream().map(CommentDto :: createCommentDto).toList();
    }

    public CommentDto create(Long articleId, CommentDto dto) {
        Article article = articleRepository.findById(articleId).orElseThrow(()->new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));

        Comment comment = Comment.createComment(dto,article);

        Comment created = commentRepository.save(comment);

        return CommentDto.createCommentDto(created);

    }

    public CommentDto update(Long id, CommentDto dto) {
        Comment target = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));

        target.patch(dto);

        Comment updated = commentRepository.save(target);

        return CommentDto.createCommentDto(updated);
    }

    public CommentDto delete(Long id) {
        Comment target = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글 삭제 실패! 대상 댓글이 없습니다."));

        commentRepository.delete(target);

        return CommentDto.createCommentDto(target);
    }
}
