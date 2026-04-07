/**
 * 예제: DB(member 테이블)로 로그인만 하는 최소 서버
 * 사용법: 이름을 index.js로 바꿔 쓰거나, node index-login-db-minimal.example.js 로 실행
 * 필요: env.js, db.js, .env, MySQL에 kculture_platform 스키마 + member 데이터
 */
import "./env.js"; // .env 읽어서 process.env에 올림(포트, db 비밀번호)
import express from "express"; // 웹서버, 라우팅
import cors from "cors"; // 다른 출처 react(5173)에서 이 API 호출 가능하게
import session from "express-session"; //로그인 후 세션 id 만들고 쿠키로 브라우저에 줄 때
import { pool } from "./db.js"; // mySQL 에 연결, member 테이블 조회

const PORT = Number(process.env.PORT, 10) || 3002; // 서버포트
const CLIENT_ORIGIN = process.env.CLIENT_ORIGIN || "http://localhost:5173"; // react(5173) 에서 온 요청만 허용(쿠키포함)
const SESSION_SECRET = process.env.SESSION_SECRET || "dev-secret-change-me"; // 세션 ID 암호화

const app = express();

app.use(
    cors({
        // 브라우저 : fetch(credentials:include) 로 쿠키를 실어보낼 수 있게 함
        origin: CLIENT_ORIGIN,
        credentials: true, // 프론트 엔드와 세션 연결 ok -> 로그인 유지
    }),
);
app.use(express.json()); // post 본문이 json 일 때 request.body{email, password}를 채워줌
app.use(
    session({
        // 세션 설정 : 로그인 성공 시 req.session.memberId 같은 값을 서버 메모리에 저장
        name: "kculture1.sid", // 응답(response)에 set-cookie : "kculture1.sid" 이름 붙여줌
        secret: SESSION_SECRET, // 세션 데이터 보호용
        resave: false,
        saveUninitialized: false,
        // 쿠키 설정
        cookie: {
            httpOnly: true, // js에서 못읽게 함, 원래는 document.cookie 기능으로 읽을 수 있으나 못하게 함
            maxAge: 7 * 24 * 60 * 60 * 1000, // cookie 유지 시간
            sameSite: "lax",
            secure: process.env.NODE_ENV === "production", // https 일때만 쿠키 전송
        },
    }),
);

// DB에서 가져온 한 행에서 api로 내려줄 필드만 골라서 객체로 만듦 - 비밀번호 제외(response에 비번 있으면 X)
function mapMemberRow(row) {
    if (!row) return null;
    return {
        id: row.id,
        email: row.email,
        name: row.name,
        nationality: row.nationality,
        language: row.language,
    };
}

app.get("/", (req, res) => res.send("OK")); // 서버 살아있는지 단순 체크용

app.get("/api/auth/me", async (req, res) => {
    try {
        if (!req.session.memberId) {
            // 세션에 memberId 없으면 비로그인 상태
            res.json({ member: null });
            return;
        }

        // 세션에 ID 있으면, id 로 member 조회 후, mapMemberRow로 JSON
        // -> 지금 로그인된 사용자 정보를 프론트가 새로고침 후 확인할 때 씀
        const [rows] = await pool.query(
            "SELECT id, email, name, nationality, language FROM member WHERE id = ?",
            [req.session.memberId],
        );
        res.json({ member: mapMemberRow(rows[0]) });
    } catch (e) {
        console.error(e);
        res.status(500).json({ error: "Database error." });
    }
});

app.post("/api/auth/login", async (req, res) => {
    try {
        const { email, password } = req.body; // req body 에서 email, password 읽음
        if (!email || !password) {
            res.status(400).json({ error: "email and password are required." });
            return;
        }

        // email, password 로 DB 조회
        const [rows] = await pool.query(
            "SELECT * FROM member WHERE email = ? AND password = ?",
            [email, password],
        );
        const row = rows[0];
        if (!row) {
            res.status(401).json({ error: "Invalid email or password." });
            return;
        }
        req.session.memberId = row.id; // 세션에 로그인 상태 저장
        res.json({ member: mapMemberRow(row) });
    } catch (e) {
        console.error(e);
        res.status(500).json({ error: "Database error." });
    }
});

app.post("/api/auth/join", async (req, res) => {
    try {
        const { email, password, name, nationality, language } = req.body; // req body 에서 email, password 읽음
        if (!email || !password || !name) {
            res.status(400).json({
                error: "email and password, and name are required.",
            });
            return;
        }

        // email, password 로 DB 조회
        const [dup] = await pool.query(
            "SELECT * FROM member WHERE email = ? ",
            [email],
        );

        //같은 이메일이 있으면
        if (dup.length > 0) {
            res.status(409).json({ error: "Email already exists." });
            return;
        }

        const row = rows[0];
        if (!row) {
            res.status(401).json({ error: "Invalid email or password." });
            return;
        }
        req.session.memberId = row.id; // 세션에 로그인 상태 저장
        res.json({ member: mapMemberRow(row) });

        const [result] = await pool.query(
            "INSERT INTO member (email, password, name, nationality, language) VALUES (?, ?, ?, ?, ?)",
            [email, password, name, nationality || null, language || "en"],
            // nationality 없으면 null, language 없으면 기본 en
        );

        req.session.memberId = result.insertId; // 새로 생긴 회원의 id가 들어옴 가입 직후 세션에 회원 id넣음 -> 가입 동시 로그인
        const [rows] = await pool.query(
            "SELECT id, email, name, nationality, language FROM member WHERE id = ?",
            [result.insertId],
        );
        // 방금 넣은 id로 select 해서 비밀번호 제외 필드만 가져옴

        res.status(201).json({ member: mapMemberRow(rows[0]) });
        //201 created 성공메시지 200 ok: 요청을 잘 처리했음 201- 없던것이 새로 생성됨
    } catch (e) {
        console.error(e);
        res.status(500).json({ error: "Database error." });
    }
});
// 이메일 중복 검사 -> 회원 insert, 세션에 memberId 넣고 -> 비밀번호 없는 회원 정보 json으로 201 응답하는 회원가입 API

app.post("/api/auth/logout", (req, res) => {
    req.session.destroy((err) => {
        // 세션 삭제
        if (err) {
            res.status(500).json({ error: "Could not log out." });
            return;
        }
        res.clearCookie("kculture1.sid", { path: "/" }); // 쿠키 제거
        res.json({ ok: true });
    });
});

/** ---------- categories ---------- : 카테고리 목록만 부른 것임 */
app.get("/api/categories", async (_req, res) => {
    try {
        const [rows] = await pool.query(
            "SELECT id, code, name_en AS nameEn, name_ko AS nameKo, icon, sort_order AS sortOrder FROM category ORDER BY sort_order",
        );
        res.json(rows);
    } catch (e) {
        console.error(e);
        res.status(500).json({ error: "Database error." });
    }
});

const POST_SELECT_LIST = `p.id, p.category_id AS categoryId, p.member_id AS memberId, p.title, p.content,
        p.image_filename AS imageFilename, p.view_count AS viewCount, p.created_at AS createdAt, p.updated_at AS updatedAt,
        m.name AS memberName, c.name_en AS categoryName, c.icon AS categoryIcon`;

const POST_SELECT_DETAIL = `p.id, p.category_id AS categoryId, p.member_id AS memberId, p.title, p.content,
      p.image_filename AS imageFilename, p.view_count AS viewCount, p.created_at AS createdAt, p.updated_at AS updatedAt,
      m.name AS memberName, m.nationality, c.name_en AS categoryName, c.icon AS categoryIcon`;

/** ---------- posts list ---------- */
app.get("/api/posts", async (req, res) => {
    try {
        const categoryId = Number(req.query.categoryId) || 0;
        const page = Math.max(1, Number(req.query.page) || 1);
        const pageSize = Math.min(
            50,
            Math.max(1, Number(req.query.pageSize) || 10),
        );
        const start = (page - 1) * pageSize;

        let listSql;
        let countSql;
        const params = [];
        if (categoryId > 0) {
            listSql = `SELECT ${POST_SELECT_LIST}
        FROM post p JOIN member m ON p.member_id = m.id JOIN category c ON p.category_id = c.id
        WHERE p.category_id = ? ORDER BY p.created_at DESC LIMIT ?, ?`;
            params.push(categoryId, start, pageSize);
            countSql = "SELECT COUNT(*) AS cnt FROM post WHERE category_id = ?";
        } else {
            listSql = `SELECT ${POST_SELECT_LIST}
        FROM post p JOIN member m ON p.member_id = m.id JOIN category c ON p.category_id = c.id
        ORDER BY p.created_at DESC LIMIT ?, ?`;
            params.push(start, pageSize);
            countSql = "SELECT COUNT(*) AS cnt FROM post";
        }

        const [list] = await pool.query(listSql, params);
        const [countRows] =
            categoryId > 0
                ? await pool.query(countSql, [categoryId])
                : await pool.query(countSql);
        const total = countRows[0]?.cnt ?? 0;
        const totalPages = Math.max(1, Math.ceil(total / pageSize));

        res.json({
            posts: list,
            total,
            page,
            pageSize,
            totalPages,
            categoryId,
        });
    } catch (e) {
        console.error(e);
        res.status(500).json({ error: "Database error." });
    }
});

app.listen(PORT, () => {
    console.log(`Login API (DB) http://localhost:${PORT}`);
});
// 지정한 포트에서 http 서버를 열고, 콘솔에 주소를 출력

// 프론트는 POST /api/auth/login에 이메일·비번을 JSON으로 보내고, 성공 시 서버가 세션 + 쿠키를 준다.
// 이후 요청은 같은 쿠키로 memberId를 알 수 있고, GET /api/auth/me로 회원 정보를 다시 받을 수 있다.
// DB는 pool이 member 테이블을 조회할 때만 사용된다.
