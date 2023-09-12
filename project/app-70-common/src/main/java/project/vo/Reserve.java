package project.vo;

import java.sql.Timestamp;
import java.util.Objects;

public class Reserve {
    private int no;
    private Member reserveName;
    private Book reserveBook;
    private Timestamp reserveDate;

    @Override
    public int hashCode() {
        return Objects.hash(no);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        Reserve reserve = (Reserve) obj;

        if (this.getNo() != reserve.getNo()) {
            return false;
        }

        return true;
    }

    public int getNo() { return no; }

    public void setNo(int no) { this.no = no; }

    public Member getReserveName() { return reserveName; }

    public void setReserveName(Member reserveName) { this.reserveName = reserveName; }

    public Book getReserveBook() { return reserveBook; }

    public void setReserveBook(Book reserveBook) { this.reserveBook = reserveBook; }

    public Timestamp getReserveDate() { return reserveDate; }

    public void setReserveDate(Timestamp reserveDate) { this.reserveDate = reserveDate; }
}
