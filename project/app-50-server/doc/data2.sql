-- project_member 테이블 예제 데이터
insert into project_member(member_no, name, email, password, gender) 
  values(1, 'aaa', 'aaa@test.com', sha1('1111'), 'W');
insert into project_member(member_no, name, email, password, gender) 
  values(2, 'bbb', 'bbb@test.com', sha1('1111'), 'M');
insert into project_member(member_no, name, email, password, gender) 
  values(3, 'ccc', 'ccc@test.com', sha1('1111'), 'W');
insert into project_member(member_no, name, email, password, gender) 
  values(4, 'ddd', 'ddd@test.com', sha1('1111'), 'M');
insert into project_member(member_no, name, email, password, gender) 
  values(5, 'eee', 'eee@test.com', sha1('1111'), 'W');
insert into project_member(member_no, name, email, password, gender) 
  values(6, 'fff', 'fff@test.com', sha1('1111'), 'M');
  
-- project_book 테이블 예제 데이터
insert into project_book(book_no, booktitle, author, name)
  values(21, '노인과바다', 'a', 1);
insert into project_book(book_no, booktitle, author, name)
  values(22, '노인과바다', 'a', 2);
insert into project_book(book_no, booktitle, author, name)
  values(23, '멈추지않는도전', 'b', 3);
insert into project_book(book_no, booktitle, author, name)
  values(24, '멈추지않는도전', 'b', 4);
insert into project_book(book_no, booktitle, author, name)
  values(25, '챔스', 'c', 5);
insert into project_book(book_no, booktitle, author, name)
  values(26, '챔스', 'c', 6);
  
UPDATE project_book
  SET return_date = rental_date + INTERVAL 7 DAY;

-- project_board 테이블 예제 데이터
insert into project_board(board_no, title, content, writer, password)
  values(11, '제목1', '내용', 1, sha1('1111'));
insert into project_board(board_no, title, content, writer, password)
  values(12, '제목2', '내용', 1, sha1('1111'));
insert into project_board(board_no, title, content, writer, password)
  values(13, '제목3', '내용', 3, sha1('1111'));
insert into project_board(board_no, title, content, writer, password)
  values(14, '제목4', '내용', 4, sha1('1111'));
insert into project_board(board_no, title, content, writer, password)
  values(15, '제목5', '내용', 5, sha1('1111'));
insert into project_board(board_no, title, content, writer, password)
  values(16, '제목6', '내용', 5, sha1('1111'));
insert into project_board(board_no, title, content, writer, password)
  values(17, '제목7', '내용', 5, sha1('1111'));