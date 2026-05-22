# board-login-img-security-oauth-react

Spring Boot 작업형 4문제 통합 프로젝트.

## 환경

- Java 17
- Spring Boot 4.0.6
- MySQL 8.x
- Maven

## 실행 방법

1. MySQL에 데이터베이스 생성
```sql
   CREATE DATABASE boardapp_db DEFAULT CHARACTER SET utf8mb4;
```
2. `application.yml`의 DB 사용자/비밀번호 본인 환경에 맞게 수정
3. IntelliJ에서 메인 클래스 실행 → `http://localhost:8088`

## 포트

- 백엔드: `8088`

## API 목록 (작업형 1 — CRUD)

| 메서드 | 경로 | 설명 | 응답 |
|--------|------|------|------|
| POST | `/api/members` | 회원가입 | 201 / 400 / 409 |
| GET | `/api/posts` | 글 목록 | 200 |
| GET | `/api/posts/{id}` | 글 상세 | 200 / 404 |
| POST | `/api/posts` | 글 등록 | 201 / 400 / 404 |
| PUT | `/api/posts/{id}` | 글 수정 | 200 / 400 / 404 |
| DELETE | `/api/posts/{id}` | 글 삭제 | 204 / 404 |

## 진행 상태

- [x] 작업형 1 — CRUD API
- [ ] 작업형 2 — Spring Security + 세션 로그인
- [ ] 작업형 3 — 이미지 업로드
- [ ] 작업형 4 — OAuth2 + React