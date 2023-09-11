package project.vo;

import java.sql.Timestamp;
import java.util.Calendar;

public class Rent {
    private int no;
    private Member lender;
    private Book rentBook;
    private Timestamp rentalDate;
    private Timestamp returnDate;

    public int getNo() { return no; }

    public void setNo(int no) { this.no = no; }

    public Member getLender() { return lender; }

    public void setLender(Member lender) { this.lender = lender; }

    public Book getRentBook() { return rentBook; }

    public void setRentBook(Book rentBook) { this.rentBook = rentBook; }

    public Timestamp getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Timestamp rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp rentalDate) {
        if (rentalDate != null) {
            // rentalDate로부터 7일 후의 returnDate를 계산합니다.
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(rentalDate);
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            this.returnDate = new Timestamp(calendar.getTimeInMillis());
        } else {
            this.returnDate = null; // rentalDate가 null인 경우 returnDate도 null로 설정
        }
    }
}
