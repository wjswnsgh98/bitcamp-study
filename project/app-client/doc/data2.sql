-- project_member 테이블 예제 데이터
insert into project_member(member_no, name, email, password, gender) 
  values(1, 'aaa', 'aaa@test.com', '1111', 'W');
insert into project_member(member_no, name, email, password, gender) 
  values(2, 'bbb', 'bbb@test.com', '1111', 'M');
insert into project_member(member_no, name, email, password, gender) 
  values(3, 'ccc', 'ccc@test.com', '1111', 'W');
insert into project_member(member_no, name, email, password, gender) 
  values(4, 'ddd', 'ddd@test.com', '1111', 'M');
insert into project_member(member_no, name, email, password, gender) 
  values(5, 'eee', 'eee@test.com', '1111', 'W');
insert into project_member(member_no, name, email, password, gender) 
  values(6, 'fff', 'fff@test.com', '1111', 'M');
  
-- project_book 테이블 예제 데이터
insert into project_book(booktitle, author, name)
  values('노인과바다', 'a', 'aaa');
insert into project_book(booktitle, author, name)
  values('노인과바다', 'a', 'bbb');
insert into project_book(booktitle, author, name)
  values('멈추지않는도전', 'b', 'ccc');
insert into project_book(booktitle, author, name)
  values('멈추지않는도전', 'b', 'ddd');
insert into project_book(booktitle, author, name)
  values('챔스', 'c', 'eee');
insert into project_book(booktitle, author, name)
  values('챔스', 'c', 'fff');

-- project_board 테이블 예제 데이터
insert into project_board(board_no, title, content, writer, password)
  values(11, '제목1', '내용', 'aaa', '1111');
insert into project_board(board_no, title, content, writer, password)
  values(12, '제목2', '내용', 'bbb', '1111');
insert into project_board(board_no, title, content, writer, password)
  values(13, '제목3', '내용', 'ccc', '1111');
insert into project_board(board_no, title, content, writer, password)
  values(14, '제목4', '내용', 'ddd', '1111');
insert into project_board(board_no, title, content, writer, password)
  values(15, '제목5', '내용', 'eee', '1111');
insert into project_board(board_no, title, content, writer, password)
  values(16, '제목6', '내용', 'fff', '1111');
insert into project_board(board_no, title, content, writer, password)
  values(17, '제목7', '내용', 'eee', '1111');