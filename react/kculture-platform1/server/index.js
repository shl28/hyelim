/**
 * 예제: DB(member 테이블)로 로그인만 하는 최소 서버
 * 사용법: 이름을 index.js로 바꿔 쓰거나, node index-login-db-minimal.example.js 로 실행
 * 필요: env.js, db.js, .env, MySQL에 kculture_platform 스키마 + member 데이터
 */
import "./env.js"; //.env를 읽어 process.env 올림(포트,db비밀번호)
import express from "express"; //웹서버, 라우팅
import cors from "cors"; //다른출처 react(5173) 에서 이 api 호출 가능하게
import session from "express-session"; //로그인후 세션id만들고, 쿠키로 브라우저에 줄때
import { pool } from "./db.js"; //mysql 에 연결 member 테이블 조회
import multer from "multer"; //파일 업로드
import fs from "fs"; // 파일 시스템 - 파일 읽기 쓰기 폴더관리
import { dirname, join, extname } from "path"; //dirname - 폴더경로만 join '\' 폴더조각맞춰줌   extname -확장자추출
import { fileURLToPath } from "url"; //브라우저 방식file://... ,  c:\... -> 컴퓨터 방식주소변경

const __dirname = dirname(fileURLToPath(import.meta.url)); //현재 파일 위치 기준
const UPLOAD_DIR = join(__dirname, "uploads"); // /project/server/uploads
/** 서버 기동 시 업로드 디렉터리가 없으면 생성 */
if (!fs.existsSync(UPLOAD_DIR)) {
    fs.mkdirSync(UPLOAD_DIR, { recursive: true });
}

/** multer: 디스크에 저장할 경로·파일명 규칙 (타임스탬프 + 랜덤 + 확장자) */
//  어디에 / 어떻게 저장할지
const storage = multer.diskStorage({
    destination: (_req, _file, cb) => cb(null, UPLOAD_DIR), //uploaes 폴더에 저장
    filename: (_req, file, cb) => {
        const ext = extname(file.originalname) || ".jpg";
        cb(
            null,
            `${Date.now()}-${Math.random().toString(36).slice(2, 10)}${ext}`,
        );
    }, //17428985456-ab12cd56.jpg
    //36진수 (0-9 + a-z) .slice(2, 10) 앞에 0을떼고 8글자만 가져옮
    //cb는 콜백  cb callback 첫번째 인자가(에러발생여부) null  성공, 고유의파일명 multer전달
});

/** multer 인스턴스: 단일 이미지 필드, 5MB 제한, JPEG/PNG/GIF/WebP만 허용 */
const upload = multer({
    storage,
    limits: { fileSize: 5 * 1024 * 1024 }, //파일크기제한
    fileFilter: (_req, file, cb) => {
        const ok = /^image\/(jpeg|png|gif|webp)$/.test(file.mimetype);
        cb(
            ok
                ? null
                : new Error(
                      "JPEG, PNG, GIF, WebP 이미지만 업로드할 수 있습니다.",
                  ),
            ok,
        );
    },
});

const PORT = Number(process.env.PORT, 10) || 3002;
const CLIENT_ORIGIN = process.env.CLIENT_ORIGIN || "http://localhost:5173";
const SESSION_SECRET =
    process.env.SESSION_SECRET || "kculture-dev-secret-change-in-production";

const app = express();

app.set("trust proxy", 1);

app.use(
    cors({
        //브라우저 : fetch(credentials:'include') 로 쿠키를 실어보낼수있게함
        origin: CLIENT_ORIGIN,
        credentials: true, //프론트 엔드와 세션 연결 ok - 로그인유지
    }),
);
app.use(express.json()); //post 본문이 json일때 req.body{email, password} 를 채워줌
app.use(
    session({
        //세션설정 -로그인성공시 req.session.meberId 같은 값을 서버메모리저장
        name: "kculture1.sid", //응답 set-Cookie : kculture1.sid 을 붙여줌
        secret: SESSION_SECRET, //세션데이터 보호
        resave: false,
        saveUninitialized: false,
        cookie: {
            //쿠키설정
            httpOnly: true, //js 못일게, document.cookie 못읽음
            maxAge: 7 * 24 * 60 * 60 * 1000, //쿠키유지시간
            sameSite: "lax",
            secure: process.env.NODE_ENV === "production", //https일때문 쿠키 전송
        },
    }),
);
/** 업로드 이미지 브라우저 제공 — HomePage 썸네일 등 `/uploads/파일명` */
app.use("/uploads", express.static(UPLOAD_DIR));
//DB에서 가져온 한 행에서 api로 내려줄 필드만 골라서객체로만듦
// - 비밀번호 넣지않음- 응답에 비번 나가면 안됨
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

app.get("/", (req, res) => res.send("OK")); //서버살아있는지 단순체크

app.get("/api/auth/me", async (req, res) => {
    try {
        if (!req.session.memberId) {
            //세션에 memberId 없으면 비로그인
            res.json({ member: null });
            return;
        }
        //세션에 아이디 id있으면 id로 member조회후 mapMemberRow Json
        //-> 지금 로그인된 사용자 정보를 프론트가 새로고침후 확인함때 씀
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
        const { email, password } = req.body; //res.body에서 email, password 읽음
        if (!email || !password) {
            res.status(400).json({ error: "email and password are required." });
            return;
        }
        //Db조회
        const [rows] = await pool.query(
            "SELECT * FROM member WHERE email = ? AND password = ?",
            [email, password],
        );
        const row = rows[0];
        if (!row) {
            res.status(401).json({ error: "Invalid email or password." });
            return;
        }
        req.session.memberId = row.id; //세션에 로그인상태저장
        res.json({ member: mapMemberRow(row) });
    } catch (e) {
        console.error(e);
        res.status(500).json({ error: "Database error." });
    }
});

app.post("/api/auth/join", async (req, res) => {
    try {
        const { email, password, name, nationality, language } = req.body; //res.body에서 email, password 읽음
        if (!email || !password || !name) {
            res.status(400).json({
                error: "email, password , and name are required.",
            });
            return;
        }
        //DB 이메일 중복확인
        const [dup] = await pool.query(
            "SELECT * FROM member WHERE email = ? ",
            [email],
        );
        if (dup.length > 0) {
            //같은 이메일이 있으면
            res.status(409).json({ error: "Email already exists." });
            return;
        }
        const [result] = await pool.query(
            "INSERT INTO member (email, password, name, nationality, language) VALUES (?, ?, ?, ?, ?)",
            [email, password, name, nationality || null, language || "en"],
            //nationality 는 없으면 null , language 없으면 en']
        );
        req.session.memberId = result.insertId; //새로 생긴 회원의 id가 들어온다. 가입직후 세션에 회원id를 넣는다. - 가입동시 로그인
        const [rows] = await pool.query(
            "SELECT id, email, name, nationality, language FROM member WHERE id = ?",
            [result.insertId],
        );
        //방금 넣은 id로 select 해서 비밀번호 제회 필드만 가져온다.
        res.status(201).json({ member: mapMemberRow(rows[0]) });
        // 201 created 성공메시지 200 ok: 요청을 잘처리  201 -  없던것이 생성 성공 메시지
    } catch (e) {
        console.error(e);
        res.status(500).json({ error: "Database error." });
    }
});
//이메일 중복검사 -> 회원 insert, 세션에 memberId 넣고 -> 비번없는 회원 정보 json으로 201 응닫하는
//회원가입 api

app.post("/api/auth/logout", (req, res) => {
    req.session.destroy((err) => {
        //세션 삭제
        if (err) {
            res.status(500).json({ error: "Could not log out." });
            return;
        }
        res.clearCookie("kculture1.sid", { path: "/" }); //쿠키제거
        res.json({ ok: true });
    });
});

// categories
/** ---------- categories ---------- 카테고리 목록만 부른것임 */
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
//sql 에서 가져올 컬럼들을 미리 정의
//p.id  p.title  - 코드 중복방지 유지보수가 편하게
// List = 목록   Detail = 상세페이지용(nationality 추가)
const POST_SELECT_LIST = `p.id, p.category_id AS categoryId, p.member_id AS memberId, p.title, p.content,
        p.image_filename AS imageFilename, p.view_count AS viewCount, p.created_at AS createdAt, p.updated_at AS updatedAt,
        m.name AS memberName, c.name_en AS categoryName, c.icon AS categoryIcon`;

const POST_SELECT_DETAIL = `p.id, p.category_id AS categoryId, p.member_id AS memberId, p.title, p.content,
      p.image_filename AS imageFilename, p.view_count AS viewCount, p.created_at AS createdAt, p.updated_at AS updatedAt,
      m.name AS memberName, m.nationality, c.name_en AS categoryName, c.icon AS categoryIcon`;

/** ---------- posts list ---------- */
//요청 - 조건확인 - sql 실행 - 결과 + 페이지 정보반환
app.get("/api/posts", async (req, res) => {
    //GET 요청으로 게시글 목록을 가져오는 api
    try {
        const categoryId = Number(req.query.categoryId) || 0; //카테고리 필터
        const page = Math.max(1, Number(req.query.page) || 1); //현재페이지
        const pageSize = Math.min(
            50,
            Math.max(1, Number(req.query.pageSize) || 10),
        ); //한페이지 글의 갯수
        const start = (page - 1) * pageSize;
        //1페이지 (1-1) * 10 =0 ->0번데이터부터 10개가져와라(1-10)
        //2페이지 (2-1) *10 10번 데이터 부터 10가져와라 (11-20)
        //3페이지 (3-1) *10 20번 데이터 부터 10가져와라 (21-30)

        let listSql;
        let countSql;
        const params = [];
        if (categoryId > 0) {
            //특정 카테고리만 조회 - 글 + 작성자 + 카테고리 같이가져옴
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

        const [list] = await pool.query(listSql, params); //글목록 가져오기
        const [countRows] =
            categoryId > 0
                ? await pool.query(countSql, [categoryId]) //카테고리가 있는 총갯수
                : await pool.query(countSql); //모든 총갯수
        const total = countRows[0]?.cnt ?? 0;
        const totalPages = Math.max(1, Math.ceil(total / pageSize));
        //총페이지   글 26개 / 페이지 10 개  -> 3페이지
        res.json({
            //응답 반환
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
//게시글 목을 조건 + 페이지 에 맞게 DB에서 가져와서 Json으로 반환하는 api
//react가 받는 데이터
// {
//   "posts": [...],
//   "total": 100,
//   "page": 1,
//   "pageSize": 10,
//   "totalPages": 10,
//   "categoryId": 0
// }

//post : 게시글 작성
// requireAuth(로그인 체크) , upload.single('image') 파일 업로드 처리 실제로직실행
//로그인테크->파일업로드 -> 데이터 검증 -> db저장 -> 결과반환
app.post(
    "/api/posts",
    requireAuth,
    (req, res, next) => {
        upload.single("image")(req, res, (err) => {
            if (err) {
                res.status(400).json({
                    error: err.message || "Upload failed.",
                });
                return; //파일크기 초가/ 확장자 문제등을 처리
            }
            next();
        });
    },
    async (req, res) => {
        try {
            const categoryId = Number(req.body.categoryId);
            const title = req.body.title;
            const content = req.body.content;
            if (!categoryId || !title || String(title).trim() === "") {
                res.status(400).json({
                    error: "categoryId and title are required.",
                });
                return;
            }
            let imageFilename = null;
            if (req.file) {
                //이미지파일 - 업로드된 파일 있으며
                imageFilename = req.file.filename; //파일명 저장
            }
            const [result] = await pool.query(
                //DB저장
                "INSERT INTO post (category_id, member_id, title, content, image_filename) VALUES (?, ?, ?, ?, ?)",
                [
                    categoryId,
                    req.session.memberId, //세션 저장 id - 로그인사용자
                    String(title).trim(), //제목 trim처리
                    content != null ? String(content) : "", // 내용 null 방지
                    imageFilename,
                ],
            );
            res.status(201).json({ id: result.insertId }); //저장 성공시 201과 아이디 반환
        } catch (e) {
            console.error(e);
            res.status(500).json({ error: "Database error." });
        }
    },
);

// 수정  patch - 부분수정 put-전체수정
app.patch(
    "/api/posts/:id",
    requireAuth,
    (req, res, next) => {
        upload.single("image")(req, res, (err) => {
            //image 이름으로 파일업로드 바등ㅁ
            if (err) {
                //res.file -저장
                res.status(400).json({
                    error: err.message || "Upload failed.",
                });
                return; //파일크기 초가/ 확장자 문제등을 처리
            }
            next();
        });
    },
    async (req, res) => {
        try {
            const id = Number(req.params.id);
            const title = req.body.title;
            const content = req.body.content;
            const removeImage =
                req.body.removeImage === "1" || req.body.removeImage === "true";
            if (!id) {
                res.status(400).json({ error: "Invalid id." });
                return;
            }
            //권한체크 - 기존 게시글 조회
            const [rows] = await pool.query(
                "SELECT image_filename AS imageFilename FROM post WHERE id = ? AND member_id = ?",
                [id, req.session.memberId],
            );
            if (!rows.length) {
                //글이 없거나 남의 글이면 -> 접근금지
                res.status(403).json({ error: "Forbidden or not found." });
                return;
            }
            const oldName = rows[0].imageFilename; //기존 이미지 파일 저장
            let newName = oldName;
            if (removeImage) {
                //이미지 삭제처리
                await unlinkImageFilename(oldName);
                newName = null;
            }
            if (req.file) {
                //새로운 이미지 올라오면
                await unlinkImageFilename(oldName); //기존이미지 삭제
                newName = req.file.filename; //새파일명 저장
            }
            const [r] = await pool.query(
                //db업데이트
                "UPDATE post SET title = ?, content = ?, image_filename = ? WHERE id = ? AND member_id = ?",
                [
                    String(title ?? "").trim(),
                    content != null ? String(content) : "",
                    newName,
                    id,
                    req.session.memberId,
                ],
            );
            //실제로 수정된 행이 없으면 실패
            if (r.affectedRows === 0) {
                res.status(403).json({ error: "Forbidden or not found." });
                return;
            }
            res.json({ ok: true }); //성공응답
        } catch (e) {
            console.error(e);
            res.status(500).json({ error: "Database error." });
        }
    },
);

//안전 삭제 - async 비동기함수(파일 삭제 기다림)
async function unlinkImageFilename(filename) {
    //filename - 삭에 파일이름
    if (!filename || typeof filename !== "string") return; //유효성 - filename없거나 null이나 숫자방지
    if (filename.includes("..") || /[\\/]/.test(filename)) return;
    // ../../etc/paswd - 서버 파일 접근시도 또는 '/' - 리눅스 경로 차단  '\' 윈도우 경로 차단
    const full = join(UPLOAD_DIR, filename); //실제 경로 생성 - 안전하게 경로 합침
    try {
        await fs.promises.unlink(full); //실제 파일 삭제
    } catch {
        /* ignore */
    }
}

/** DELETE: 본인 글 삭제 (첨부 이미지 파일도 함께 삭제) */
app.delete("/api/posts/:id", requireAuth, async (req, res) => {
    try {
        const id = Number(req.params.id);
        const [prev] = await pool.query(
            // 본인글 조회, 이미지 파일명 가져오기
            "SELECT image_filename AS imageFilename FROM post WHERE id = ? AND member_id = ?",
            [id, req.session.memberId],
        );
        const [r] = await pool.query(
            "DELETE FROM post WHERE id = ? AND member_id = ?",
            [id, req.session.memberId],
        );
        if (r.affectedRows === 0) {
            res.status(403).json({ error: "Forbidden or not found." });
            return;
        }
        if (prev[0]?.imageFilename) {
            await unlinkImageFilename(prev[0].imageFilename);
            // db삭제 성공 후 이미지 파일(실제파일도 삭제)
        }
        res.json({ ok: true });
    } catch (e) {
        console.error(e);
        res.status(500).json({ error: "Database error." });
    }
});

/** 게시글 ID 한 건: 상세 컬럼으로 JOIN 조회, 없으면 null */
// '글 한건만' 가져오기
async function loadPostRow(id) {
    const [rows] = await pool.query(
        `SELECT ${POST_SELECT_DETAIL}
      FROM post p JOIN member m ON p.member_id = m.id JOIN category c ON p.category_id = c.id WHERE p.id = ?`,
        [id],
    );
    return rows[0] ?? null;
}
//url로 넘어온 숫자 id로 post 한 줄을 찾고 , 작성자(member), 카테고리(category) join 같이 붙임
//POST_SELECT_DETAIL 이라서 목록보다 상세용 컬럼(ex - nationality)이 포함
//없으면 null 있으면 객체한개를 돌려줍니다.

/** ---------- post detail (+ view++) ---------- */
app.get("/api/posts/:id", async (req, res) => {
    try {
        const id = Number(req.params.id); //숫자 id 로 변환
        if (!id) {
            //  0/null 이면 404
            res.status(404).json({ error: "Not found." });
            return;
        }
        const post = await loadPostRow(id);
        if (!post) {
            res.status(404).json({ error: "Not found." });
            return;
        }
        //디비 조회수 증가 업데이트
        await pool.query(
            "UPDATE post SET view_count = view_count + 1 WHERE id = ?",
            [id],
        );
        post.viewCount = (post.viewCount ?? 0) + 1; //클라이언트에 증가해서 보냄

        const [comments] = await pool.query(
            //특정 게시글 댓글목록을 db에서 조회 comments배열에 저장
            `SELECT c.id, c.post_id AS postId, c.member_id AS memberId, c.content, c.created_at AS createdAt,
        m.name AS memberName, m.nationality
        FROM comment c JOIN member m ON c.member_id = m.id WHERE c.post_id = ? ORDER BY c.created_at`,
            [id],
        );
        //comment 댓글테이블과 member 작성테이블 join -> 댓글 + 작성자 정보같이 가져옴
        //조건  WHERE c.post_id = ? 해당 게시글의 댓글만 조회
        // 댓글 + 작성자 정보를 Join해서 특정 게시글 기준으로 가져오는 쿼리

        res.json({ post, comments });
    } catch (e) {
        console.error(e);
        res.status(500).json({ error: "Database error." });
    }
});

// [
//   {
//     id: 1,
//     postId: 10,
//     memberId: 3,
//     content: "댓글 내용",
//     createdAt: "2026-04-09",
//     memberName: "홍길동",
//     nationality: "Korea"
//   }
// ]

/** GET: 편집 폼용 데이터 (본인 글만, 조회수 증가 없음) */
// /api/posts/:id/edit 로그인체크 -> id 유효성검사 - 게시글조회 - 작성자보인인지 - 데이터반환

//로그인이 안되있으면 여기 출입이 안됨 requireAuth
app.get("/api/posts/:id/edit", requireAuth, async (req, res) => {
    try {
        const id = Number(req.params.id);
        if (!id) {
            //잘못된 url /posts/sdfds
            res.status(404).json({ error: "Not found." });
            return;
        }
        const post = await loadPostRow(id); //게시글조회(id)
        if (!post) {
            //게시글 없으면 404
            res.status(404).json({ error: "Not found." });
            return;
        }
        if (post.memberId !== req.session.memberId) {
            //작성자 본인 확인
            res.status(403).json({ error: "Forbidden." });
            return;
        }
        res.json({ post }); //json형식으로 받는다.
    } catch (e) {
        console.error(e);
        res.status(500).json({ error: "Database error." });
    }
});

// {
//   "post": {
//     "id": 16,
//     "title": "제목",
//     "content": "내용",
//     "imageFilename": "abc.jpg",
//     "memberId": 3
//   }
// }

/** ---------- comments ---------- */

/** POST: 특정 글에 댓글 추가 (로그인 사용자) */
//post -> 데이터생성(댓글작성)
///api/posts/16/comments -> 특정게시글 댓글
// requireAuth 로그인 필수
app.post("/api/posts/:id/comments", requireAuth, async (req, res) => {
    try {
        const postId = Number(req.params.id); //16 url에서 가져온 id
        const { content } = req.body; // 입력한 댓글 내용
        if (!postId || !content || String(content).trim() === "") {
            res.status(400).json({ error: "content is required." });
            return;
        }
        const [result] = await pool.query(
            "INSERT INTO comment (post_id, member_id, content) VALUES (?, ?, ?)",
            [postId, req.session.memberId, String(content).trim()],
            // req.session.memberId -> 로그인한 아이디
        );
        res.status(201).json({ id: result.insertId }); //201->생성성공  / insertId-방금 생성된 댓글아이디
    } catch (e) {
        console.error(e);
        res.status(500).json({ error: "Database error." });
    }
});

//세션에 로그인(memberId) 가 덦으면 401, 있으면 다음 미들웨어로
function requireAuth(req, res, next) {
    if (!req.session.memberId) {
        res.status(401).json({ error: "Login required." });
        return;
    }
    next();
}

app.listen(PORT, () => {
    console.log(`Login API (DB) http://localhost:${PORT}`);
});
//지정한 포트에서 http 서버를 열고, 콘솔에 주소를 출력

// 프론트는 POST /api/auth/login에 이메일·비번을 JSON으로 보내고, 성공 시 서버가 세션 + 쿠키를 준다.
// 이후 요청은 같은 쿠키로 memberId를 알 수 있고, GET /api/auth/me로 회원 정보를 다시 받을 수 있다.
// DB는 pool이 member 테이블을 조회할 때만 사용된다.
