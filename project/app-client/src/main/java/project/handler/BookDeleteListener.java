package project.handler;

import project.dao.BookDao;
import project.vo.Book;
import project.vo.Member;
import util.ActionListener;
import util.BreadcrumbPrompt;

public class BookDeleteListener implements ActionListener{
  BookDao bookDao;

  public BookDeleteListener(BookDao bookDao) {
    this.bookDao = bookDao;
  }

  String[][] BOOKS = BookDao.BOOKS;

  @Override
  public void service(BreadcrumbPrompt prompt) {
    String bookTitle = prompt.inputString("도서 제목? ");
    String author = prompt.inputString("저자? ");
    String lender = prompt.inputString("대여자 이름? ");

    try {
      // 대여자 이름으로 대여자 번호 찾기
      int lenderNo = bookDao.findMemberNoByName(lender);
      if (lenderNo == -1) {
        System.out.println("해당 이름의 대여자는 없습니다!");
        return;
      }

      // 리스트에서 도서 삭제
      Book book = new Book();
      book.setBookTitle(bookTitle);
      book.setAuthor(author);
      Member member = new Member();
      member.setNo(lenderNo);
      member.setName(lender);
      book.setName(member);

      int deletedRows = bookDao.delete(book);
      if (deletedRows > 0) {
        System.out.println("도서 삭제 성공!");
      } else {
        System.out.println("해당 대여자의 도서가 없습니다!");
      }

      // BOOKS에서 같은 도서 제목의 수량을 1 증가
      Book deletedBook = bookDao.findBy(lender);
      if (deletedBook != null) {
        for (int i = 0; i < BOOKS.length; i++) {
          if (deletedBook.getBookTitle().equals(BOOKS[i][0])) {
            int count = Integer.parseInt(BOOKS[i][1]);
            BOOKS[i][1] = String.valueOf(count + 1);
            break; // 해당 도서를 찾았으므로 더 이상 반복할 필요가 없음
          }
        }
      }

    } catch (Exception e) {
      // 예외처리
      e.printStackTrace();
      System.out.println("도서 반납 중 오류가 발생하였습니다!");
    }
  }

  //    @Override
  //    public void service(BreadcrumbPrompt prompt) {
  //      String lender = prompt.inputString("대여자 이름? ");
  //
  //      try {
  //        // 리스트에서 도서 삭제
  //        Book deletedBook = bookDao.findBy(lender);
  //        if (deletedBook == null) {
  //          System.out.println("해당 이름의 대여자는 없습니다!");
  //          return;
  //        }
  //        bookDao.delete(lender);
  //
  //        // BOOKS에서 같은 도서 제목의 수량을 1 증가
  //        for (int i = 0; i < BOOKS.length; i++) {
  //          if (deletedBook.getBookTitle().equals(BOOKS[i][0])) {
  //            int count = Integer.parseInt(BOOKS[i][1]);
  //            BOOKS[i][1] = String.valueOf(count + 1);
  //            break; // 해당 도서를 찾았으므로 더 이상 반복할 필요가 없음
  //          }
  //        }
  //      } catch (Exception e) {
  //        // 예외처리
  //        e.printStackTrace();
  //        System.out.println("도서 반납 중 오류가 발생하였습니다!");
  //      }
  //}
}