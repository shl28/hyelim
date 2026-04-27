package com.example.secondproject.repository;

import com.example.secondproject.entity.Article;
import com.example.secondproject.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest

class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void findByArticleId() {
        Long articleId = 4L;

        List<Comment> comments = commentRepository.findByArticleId(articleId);

        Article article = new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ");
        Comment a = new Comment(1L, article, "Park", "굳 윌 헌팅");
        Comment b = new Comment(2L, article, "Kim", "아이 엠 샘");
        Comment c = new Comment(3L, article, "Choi", "쇼생크의 탈출");

        List<Comment> expected = Arrays.asList(a, b, c);

        assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글 출력!");
    }

    @Test
    void findByNickname() {
        String nickname = "Park";

        List<Comment> comments = commentRepository.findByNickname(nickname);

        Comment a = new Comment(1L, new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ"), nickname, "굳 윌 헌팅");
        Comment b = new Comment(4L, new Article(5L, "당신의 소울 푸드는?", "댓글 ㄱㄱ"), nickname, "치킨");
        Comment c = new Comment(7L, new Article(6L, "당신의 취미는?", "댓글 ㄱㄱㄱ"), nickname, "조깅");
        List<Comment> expected = Arrays.asList(a, b, c);

        assertEquals(expected.toString(), comments.toString(), "Park의 모든 댓글 출력!");
    }
}