package dto;

import java.util.Date;

public class BookDTO {

    private Long id;
    private String title;
    private String author;
    private String status;
    private Date borrowedDate;

    public BookDTO() {
    }

    public BookDTO(Long id, String title, String author, Boolean isBorrowed, String borrowedBy, Date borrowedDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.borrowedDate = borrowedDate;

        if (isBorrowed != null && isBorrowed) {
            this.status = "Borrowed by " + (borrowedBy);
        } else {
            this.status = "Available";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }
}
