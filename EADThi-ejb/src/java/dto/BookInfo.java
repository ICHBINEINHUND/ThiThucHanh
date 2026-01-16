package dto;

import java.util.Date;

public class BookInfo {

    private Long bookId;
    private String isbn;
    private String title;
    private String author;
    private String availabilityStatus;
    private Date borrowDate;

    public BookInfo() {
    }

    public BookInfo(Long bookId, String isbn, String title, String author, Boolean borrowed, String borrowedBy,
            Date borrowDate) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.borrowDate = borrowDate;

        if (borrowed != null && borrowed) {
            this.availabilityStatus = "Borrowed by " + (borrowedBy != null ? borrowedBy : "Unknown");
        } else {
            this.availabilityStatus = "Available";
        }
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }
}
