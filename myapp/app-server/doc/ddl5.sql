-- 방명록
DROP TABLE IF EXISTS sns_guestbook RESTRICT;

-- 방명록좋아요
DROP TABLE IF EXISTS sns_guestbook_like RESTRICT;

-- 방명록
CREATE TABLE sns_guestbook (
  gbno     INTEGER      NOT NULL COMMENT '번호', -- 번호
  title    VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content  MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  likes   INTEGER           NULL COMMENT '좋아요', -- 좋아요
  created_at DATETIME     NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  mno      INTEGER      NOT NULL COMMENT '회원번호', -- 작성자 번호
  FOREIGN KEY (mno) REFERENCES sns_member (mno),
  mem_no   INTEGER      NOT NULL COMMENT '회원번호',  -- 당사자 번호
  FOREIGN KEY (mem_no) REFERENCES sns_mypage (mno)
)
COMMENT '방명록';

-- 방명록
ALTER TABLE sns_guestbook
  ADD CONSTRAINT PK_sns_guestbook -- 방명록 기본키
  PRIMARY KEY (
  gbno -- 번호
  );

ALTER TABLE sns_guestbook
  MODIFY COLUMN gbno INTEGER AUTO_INCREMENT COMMENT '번호';

-- 방명록 좋아요
CREATE TABLE sns_guestbook_like (
  gbno     INTEGER      NOT NULL COMMENT '번호', -- 번호
  FOREIGN KEY (gbno) REFERENCES sns_guestbook (gbno),
  mno      INTEGER      NOT NULL COMMENT '회원번호', -- 작성자 번호
  FOREIGN KEY (mno) REFERENCES sns_member (mno)
)
COMMENT '방명록좋아요';