package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
// html이 아니라 json을 반환

public class FirstApiController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/api/hello")
    public String hello() {
        return "hello world!";
    }

    // GET
    // 모두 조회
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }
//[
//    { "id": 1, "title": "...", "content": "..." }
//    { "id": 2, "title": "...", "content": "..." }
//]

    // 단건 조회
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

//    [
//      {
//        "title": "제목",
//        "content": "내용"
//      }
//    ]

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        // 1. dto -> entity 변환
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        // 2. 타겟 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청
        if (target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답
            log.info("잘못된 요청 id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 4. 업데이트 및 정상 응답(200)
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 잘못된 요청 처리
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 3. 대상 삭제
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();

        // 데이터 조희 -> 없으면 400 -> 있으면 삭제
    }
    // 조회 : GET
    // 생성 : POST
    // 수정 : PATCH
    // 삭제 : DELETE
    // @RequestBody : JSON 데이터 -> java 객체로 변경
    // ResponseEntity : 상태코드 제어
    // JSON 통신 : 프론트와 데이터 주고받기
}
