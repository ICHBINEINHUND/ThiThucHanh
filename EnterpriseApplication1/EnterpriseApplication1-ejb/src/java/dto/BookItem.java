package dto;

import java.util.Date;

public class BookItem {

    private Long bookId;
    private String bookTitle;
    private String bookAuthor;
    private String currentStatus;
    private Date dateBorrowed;

    public BookItem() {
    }

    public BookItem(Long bookId, String bookTitle, String bookAuthor, Boolean isAvailable, String borrowedBy,
            Date dateBorrowed) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.dateBorrowed = dateBorrowed;

        if (isAvailable != null && !isAvailable) {
            this.currentStatus = "Borrowed by " + (borrowedBy != null ? borrowedBy : "Unknown");
        } else {
            this.currentStatus = "Available";
        }
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Date getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(Date dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }
}
