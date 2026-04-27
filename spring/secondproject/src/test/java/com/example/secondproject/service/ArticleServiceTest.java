package com.example.secondproject.service;

import com.example.secondproject.dto.ArticleForm;
import com.example.secondproject.entity.Article;
import com.example.secondproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void index() {
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        Article d = new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ");
        Article e = new Article(5L, "당신의 소울 푸드는?", "댓글 ㄱㄱ");
        Article f = new Article(6L, "당신의 취미는?", "댓글 ㄱㄱㄱ");

        List<Article> expected = new ArrayList<>(Arrays.asList(a, b, c, d, e, f));

        List<Article> articles = articleService.index();

        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        Long id = 1L;
        Article expected = new Article(1L, "가가가가", "1111");

        Article article = articleService.show(id);

        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_성공_존재하지_않는_id_입력() {
        Long id = -1L;
        Article expected = null;

        Article article = articleService.show(id);

        assertEquals(expected, article);
    }

    @Test
    void create_성공_title과_content만_있는_dto_입력() {
        String title = "라라라라";
        String content = "7777";

        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(7L, title, content);

        Article article = articleService.create(dto);

        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void create_실패_id가_포함된_dto_입력() {
        String title = "라라라라";
        String content = "7777";

        ArticleForm dto = new ArticleForm(7L, title, content);
        Article expected = null;

        Article article = articleService.create(dto);

        assertEquals(expected, article);
    }

    @Test
    void update() {
        Long id = 1L;

        String newTitle = "가가가가(수정)";
        String newContent = "1111(수정)";
        ArticleForm dto = new ArticleForm(id, newTitle, newContent);

        Article updated = articleService.update(id, dto);

        assertNotNull(updated);

        assertEquals(id, updated.getId());
        assertEquals(newTitle, updated.getTitle());
        assertEquals(newContent, updated.getContent());
    }

    @Test
    void delete() {
        Long id = 2L;

        Article deleted = articleService.delete(id);

        assertNotNull(deleted);
        assertEquals(id, deleted.getId());

        Article after = articleService.show(id);
        assertNull(after);
    }
}