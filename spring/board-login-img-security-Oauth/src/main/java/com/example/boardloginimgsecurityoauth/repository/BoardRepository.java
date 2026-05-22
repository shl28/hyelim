package com.example.boardloginimgsecurityoauth.repository;

import com.example.boardloginimgsecurityoauth.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
//    List<Board> findAll();

    @Query("SELECT b FROM Board b JOIN FETCH b.author ORDER BY b.createdAt DESC")
    List<Board> findAllWithAuthor();

//    @Query("SELECT b FROM Board b JOIN FETCH b.author WHERE b.id = :id")
//    Optional<Board> findByIdWithAuthor(@Param("id") Long id);
//    Board(게스글)조회할 때 author(User)까지 한번에 같이 가져오고, 최신순으로 정렬
//    실제 쿼리
//    SELECT b.*, u.*
//    FROM boards b
//    JOIN users u ON b.author_id = u.id;

    // 게시글 1개를 조회하면 작성자와 이미지까지 한번에 가져오는 쿼리
    @Query("SELECT DISTINCT b FROM Board b JOIN FETCH b.author LEFT JOIN FETCH b.images WHERE b.id = :id")
    Optional<Board> findByIdWithAuthorAndImages(@Param("id") Long id);
// SELECT DISTINCT b : 이미지가 여러개라서 Board 중복조회 되는 것 제거
// distinct 없으면 게시글 1개 이미지 3개 -> 3개 나옴
// JOIN FETCH b.author 작성자도 같이 가져옴  board.getAuthor().getName() 바로 사용 가능
// LEFT JOIN FETCH b.images : 이미지 리스트까지 같이 가져온다, 이미지 없는 게시글도 조회
// innerjoin : 이미지 없는 게시글 제외
}
