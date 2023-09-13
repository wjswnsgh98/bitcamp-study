drop table project_book;
drop table project_board;
drop table project_member;
drop table project_board_file;
drop table project_rent;
drop table project_reserve;

-- 도서 추천 게시판
create table project_board(
  board_no int not null,
  title varchar(255) not null,
  content mediumtext not null,
  writer int not null,
  view_count int default 0,
  created_date datetime default now()
);

alter table project_board
  add constraint primary key (board_no),
  modify column board_no int not null auto_increment;
  
-- 게시글첨부파일
CREATE TABLE project_board_file (
  board_file_no INTEGER      NOT NULL COMMENT '번호', -- 번호
  filepath      VARCHAR(255) NOT NULL COMMENT '파일경로', -- 파일경로
  board_no      INTEGER      NOT NULL COMMENT '게시글번호' -- 게시글번호
)
COMMENT '게시글첨부파일';

-- 게시글첨부파일
ALTER TABLE project_board_file
  ADD CONSTRAINT PK_project_board_file -- 게시글첨부파일 기본키
  PRIMARY KEY (
  board_file_no -- 번호
  );

ALTER TABLE project_board_file
  MODIFY COLUMN board_file_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '번호';
  
-- 도서
create table project_book(
  book_no int not null,
  book_title varchar(255) not null,
  author varchar(20) not null,
  publisher varchar(20) not null,
  content mediumtext not null,
  count int not null,
  photo varchar(255) null
);

alter table project_book
  add constraint primary key (book_no),
  modify column book_no int not null auto_increment;

-- 도서 대여
create table project_rent(
  rent_no int not null,
  lender int not null,
  rent_book int not null,
  rental_date datetime default now(),
  return_date datetime
);

alter table project_rent
  add constraint primary key (rent_no),
  modify column rent_no int not null auto_increment;

-- 도서 예약
create table project_reserve(
  reserve_no int not null,
  reserve_name int not null,
  reserve_book int not null,
  reserve_date datetime default now()
);

alter table project_reserve
  add constraint primary key (reserve_no),
  modify column reserve_no int not null auto_increment;
  
create table project_member(
  member_no int not null,
  name varchar(50) not null,
  email varchar(40) not null,
  password varchar(100) not null,
  gender char(1) not null,
  created_date date default (current_date()),
  photo varchar(255) null
);

alter table project_member
  add constraint primary key (member_no),
  modify column member_no int not null auto_increment;

-- 회원 테이블에서 이메일 유니크 키로 설정
alter table project_member
  add constraint project_member_uk unique (email);
  
-- 게시판 작성자에 대해 외부키 설정
alter table project_board
  add constraint project_board_fk foreign key (writer) references project_member (member_no);
  
-- 대여자에 대해 외부키 설정
alter table project_rent
  add constraint project_rent_fk foreign key (lender) references project_member (member_no);

-- 대여한 도서에 대해 외부키 설정
alter table project_rent
  add constraint project_rent_fk1 foreign key (rent_book) references project_book (book_no);

-- 예약한 사람에 대한 외부키 설정
alter table project_reserve
  add constraint project_reserve_fk foreign key (reserve_name) references project_member (member_no);

-- 예약한 도서에 대한 외부키 설정
alter table project_reserve
  add constraint project_reserve_fk1 foreign key (reserve_book) references project_book (book_no);
  
-- 게시글첨부파일
ALTER TABLE project_board_file
  ADD CONSTRAINT FK_project_board_TO_project_board_file -- 게시글 -> 게시글첨부파일
  FOREIGN KEY (
  board_no -- 게시글번호
  )
  REFERENCES project_board ( -- 게시글
  board_no -- 번호
  );