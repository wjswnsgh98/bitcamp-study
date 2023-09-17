-- 게시글
DROP TABLE IF EXISTS sns_board RESTRICT;

-- 게시판유형
DROP TABLE IF EXISTS sns_board_category RESTRICT;

-- 게시글첨부파일
DROP TABLE IF EXISTS sns_board_photo RESTRICT;

-- 게시판유형
DROP TABLE IF EXISTS sns_board_category RESTRICT;

-- 게시글
CREATE TABLE sns_board (
  bno     INTEGER      NOT NULL COMMENT '번호', -- 번호
  title        VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content      MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  view_count   INTEGER      NOT NULL DEFAULT 1 COMMENT '조회수', -- 조회수
  likes   INTEGER           NULL COMMENT '좋아요', -- 좋아요
  created_date DATETIME     NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  category     INTEGER      NOT NULL COMMENT '게시판' -- 게시판
)
COMMENT '게시글';

-- 게시글
ALTER TABLE sns_board
  ADD CONSTRAINT PK_sns_board -- 게시글 기본키
  PRIMARY KEY (
  bno -- 번호
  );

ALTER TABLE sns_board
  MODIFY COLUMN bno INTEGER NOT NULL AUTO_INCREMENT COMMENT '번호';

-- 게시판유형
CREATE TABLE sns_board_category (
  sns_category_no INTEGER     NOT NULL COMMENT '번호', -- 번호
  name              VARCHAR(50) NOT NULL COMMENT '게시판이름' -- 게시판이름
)
COMMENT '게시판유형';

-- 게시판유형
ALTER TABLE sns_board_category
  ADD CONSTRAINT PK_sns_board_category -- 게시판유형 기본키
  PRIMARY KEY (
  sns_category_no -- 번호
  );

-- 게시판유형 유니크 인덱스
CREATE UNIQUE INDEX UIX_sns_board_category
  ON sns_board_category ( -- 게시판유형
    name ASC -- 게시판이름
  );

ALTER TABLE sns_board_category
  MODIFY COLUMN sns_category_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '번호';

-- 게시글첨부파일
CREATE TABLE sns_board_photo (
  bpno INTEGER      NOT NULL COMMENT '번호', -- 번호
  filepath      VARCHAR(255) NOT NULL COMMENT '파일경로', -- 파일경로
  bno      INTEGER      NOT NULL COMMENT '게시글번호' -- 게시글번호
)
COMMENT '게시글첨부파일';

-- 게시글첨부파일
ALTER TABLE sns_board_photo
  ADD CONSTRAINT PK_sns_board_photo -- 게시글첨부파일 기본키
  PRIMARY KEY (
  bpno -- 번호
  );

ALTER TABLE sns_board_photo
  MODIFY COLUMN bpno INTEGER NOT NULL AUTO_INCREMENT COMMENT '번호';

-- 게시글
ALTER TABLE sns_board
  ADD CONSTRAINT FK_sns_board_category_TO_sns_board -- 게시판유형 -> 게시글
  FOREIGN KEY (
  category -- 게시판
  )
  REFERENCES sns_board_category ( -- 게시판유형
  sns_category_no -- 번호
  );

-- 게시글첨부파일
ALTER TABLE sns_board_photo
  ADD CONSTRAINT FK_sns_board_TO_sns_board_photo -- 게시글 -> 게시글첨부파일
  FOREIGN KEY (
  bno -- 게시글번호
  )
  REFERENCES sns_board ( -- 게시글
  bno -- 번호
  );