drop table project_book;
drop table project_board;
drop table project_member;

create table project_board(
  board_no int not null,
  title varchar(255) not null,
  content text null,
  writer int not null,
  password varchar(100) null,
  view_count int default 0,
  created_date datetime default now()
);

alter table project_board
  add constraint primary key (board_no),
  modify column board_no int not null auto_increment;
  
create table project_book(
  book_no int not null,
  book_title varchar(255) not null,
  author varchar(20) not null,
  lender int not null,
  rental_date datetime default now(),
  return_date datetime
);

alter table project_book
  add constraint primary key (book_no),
  modify column book_no int not null auto_increment;
  
create table project_member(
  member_no int not null,
  name varchar(20) not null,
  email varchar(50) not null,
  password varchar(100) not null,
  gender char(1) not null,
  created_date date default (current_date())
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
  
-- 도서 대여자 이름에 대해 외부키 설정
alter table project_book
  add constraint project_book_fk foreign key (lender) references project_member (member_no);