# Board App — Spring Boot 작업형 4문제 통합 프로젝트

게시판 CRUD부터 Spring Security, 이미지 업로드, OAuth2 + React SPA까지  
4단계에 걸쳐 점진적으로 발전시킨 풀스택 프로젝트입니다.

## 기술 스택

### 백엔드
- **Java 17** (Eclipse Temurin)
- **Spring Boot 4.0.6**
- **Spring Security 7** (세션 인증 + OAuth2 Client)
- **Spring Data JPA** + **Hibernate 7**
- **MySQL 8**
- **Thumbnailator 0.4.20** (썸네일 생성)
- **Lombok**
- **Maven**

### 프론트엔드
- **React 19** + **Vite**
- **React Router**
- **axios**
- **Tailwind CSS v4**

## 실행 방법

### 사전 준비
- Java 17 이상
- MySQL 8 (root 계정)
- Node.js 18 이상

### 1) 데이터베이스 준비

```sql
CREATE DATABASE boardapp_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

`application.yml`의 DB 접속 정보를 본인 환경에 맞게 수정:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/boardapp_db?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    username: root
    password: "본인비밀번호"
```

### 2) 환경 변수 설정 (OAuth용)

IntelliJ Run Configuration에 다음 두 환경 변수를 등록:

| 변수명 | 설명 |
|---|---|
| `GOOGLE_CLIENT_ID` | Google Cloud Console에서 발급받은 클라이언트 ID |
| `GOOGLE_CLIENT_SECRET` | Google Cloud Console에서 발급받은 클라이언트 비밀번호 |

> ⚠️ Client Secret은 절대 코드나 yml에 직접 작성하지 마세요. 환경 변수만 사용하세요.

### 3) Google OAuth 클라이언트 설정

1. [Google Cloud Console](https://console.cloud.google.com/) → 프로젝트 생성
2. OAuth 동의 화면 구성 (외부 유형, 테스트 사용자에 본인 이메일 추가)
3. 사용자 인증 정보 → OAuth 클라이언트 ID 생성 (웹 애플리케이션)
    - 승인된 자바스크립트 출처:
        - `http://localhost:5173`
        - `http://localhost:8088`
    - 승인된 리디렉션 URI:
        - `http://localhost:8088/login/oauth2/code/google`

### 4) 백엔드 실행

IntelliJ에서 `BoardLoginImgSecurityOauthReactApplication.main()` 실행.  
또는 터미널에서:

```bash
./mvnw spring-boot:run
```

→ `http://localhost:8088`

### 5) 프론트엔드 실행

```bash
cd frontend
npm install   # 최초 1회만
npm run dev
```

→ `http://localhost:5173`


## 포트·URL 정리

| 구분 | URL |
|---|---|
| 백엔드 API | `http://localhost:8088` |
| 프론트엔드 | `http://localhost:5173` |
| OAuth 로그인 진입 | `GET http://localhost:8088/oauth2/authorization/google` |
| OAuth 콜백 (Google이 호출) | `http://localhost:8088/login/oauth2/code/google` |
| 업로드 이미지 접근 | `http://localhost:8088/uploads/{날짜폴더}/{파일명}` |

## API 목록 (작업형 1 — CRUD)

### 인증 (`/api/auth`)

| 메서드 | 경로 | 설명 | 응답 |
|---|---|---|---|
| POST | `/api/auth/login` | 폼 로그인 | 200 / 401 |
| GET | `/api/auth/me` | 현재 로그인 사용자 정보 | 200 / 401 |
| POST | `/api/auth/logout` | 로그아웃 | 204 |

### OAuth2 (`/oauth2`)

| 메서드 | 경로 | 설명 |
|---|---|---|
| GET | `/oauth2/authorization/google` | Google OAuth 로그인 진입 |

### 회원 (`/api/members`)

| 메서드 | 경로 | 설명 | 응답 |
|---|---|---|---|
| POST | `/api/members` | 회원가입 | 201 / 400 / 409 |

### 게시글 (`/api/posts`)

| 메서드 | 경로 | 인증 | 설명 | 응답 |
|---|---|---|---|---|
| GET | `/api/posts` | 공개 | 글 목록 | 200 |
| GET | `/api/posts/{id}` | 공개 | 글 상세 (이미지 포함) | 200 / 404 |
| POST | `/api/posts` | 로그인 필요 | 글 등록 (`multipart/form-data`) | 201 / 400 / 401 |
| PUT | `/api/posts/{id}` | 본인만 | 글 수정 (JSON) | 200 / 401 / 403 / 404 |
| DELETE | `/api/posts/{id}` | 본인만 | 글 삭제 (디스크 파일도 함께 삭제) | 204 / 401 / 403 / 404 |

### multipart 요청 형식 예시 (`POST /api/posts`)
Content-Type: multipart/form-data
[필드]
request (application/json): {"title":"...", "content":"..."}
files (image/jpeg or image/png): 파일1, 파일2 (최대 3장, 파일당 10MB)

## ✅ 작업형별 완료 현황

### 작업형 1: 회원·게시판 CRUD ✓
- [x] JPA 엔티티 설계 (Member, Post)
- [x] REST API CRUD
- [x] `@Valid` 검증 + 글로벌 예외 처리 (404, 400, 409)

### 작업형 2: Spring Security + 세션 ✓
- [x] BCrypt 비밀번호 암호화
- [x] `/api/auth/login`, `/api/auth/me`, `/api/auth/logout`
- [x] 본인 글만 수정·삭제 (403)
- [x] 비로그인 시 401

### 작업형 3: 이미지 첨부 ✓
- [x] 글 등록 시 이미지 1~3장 업로드
- [x] Thumbnailator로 썸네일 자동 생성
- [x] `/uploads/**` 정적 노출
- [x] 확장자(jpg/png) + 용량(10MB) 검증
- [x] **(확장)** 글 삭제 시 디스크 파일도 함께 삭제

### 작업형 4: OAuth2 + React SPA ✓
- [x] Google OAuth2 (OIDC) 연동
- [x] 최초 로그인 시 회원 자동 생성, 이후 정보 갱신
- [x] `oauthProvider`, `oauthProviderId`, `email` 컬럼
- [x] React 4개 화면 (로그인, 게시글 목록, 상세, 글쓰기)
- [x] 회원가입 페이지 (보너스)
- [x] CORS + 세션 쿠키 (`withCredentials`)
- [x] Vite 프록시
- [x] Client Secret은 환경 변수로 분리

## 🔐 보안 처리

- BCrypt 비밀번호 해싱 (SecurityConfig의 `PasswordEncoder` 빈)
- 로그인 실패 시 username 존재 여부 노출 X (BadCredentialsException과 UsernameNotFoundException 통합 처리)
- OAuth Client Secret은 환경 변수만 사용 (`application.yml`에 평문 X)
- CORS는 명시적 origin만 허용 (`http://localhost:5173`)
- 본인 글만 수정·삭제 가능 (Service 레이어 체크)
- 비밀번호 응답에 노출 X (Response DTO 분리)

## 🐛 개발 중 만난 주요 이슈

이번 프로젝트에서 디버깅하며 해결한 주요 이슈들:

1. **`LoginUser`의 `getPassword()`가 빈 문자열 반환**  
   IDE 자동 생성 메서드가 `return ""` 으로 채워져 있어 BCrypt 검증 실패. → DB 값 그대로 반환하도록 수정.

2. **Spring Boot 4.0의 Jackson 패키지 변경**  
   `com.fasterxml.jackson.*` → `tools.jackson.*` 로 마이그레이션.

3. **Talend API의 multipart `request` part Content-Type 누락**  
   `@RequestPart("request") PostCreateRequest` → `@RequestPart("request") String`으로 받아 직접 JSON 파싱 + 수동 검증.

4. **`MultipartFile.transferTo()`의 상대 경로 문제**  
   Tomcat 작업 디렉토리가 임시 폴더라 `./uploads`로 저장 시 경로 오류. → `Paths.get(...).toAbsolutePath().normalize()` 적용.

5. **`ddl-auto: update`가 컬럼의 NULL 제약 변경 안 함**  
   OAuth 도입 시 `password`를 nullable로 바꿔도 기존 컬럼은 그대로. → 수동 `ALTER TABLE members MODIFY password VARCHAR(100) NULL;`.

6. **OAuth 사용자의 Principal 타입 불일치**  
   `@AuthenticationPrincipal LoginUser` 는 OAuth 사용자에게 null. → `Object` + `instanceof` 패턴으로 `LoginUser` / `LoginOidcUser` 모두 처리.