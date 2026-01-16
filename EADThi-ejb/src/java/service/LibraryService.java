package service;

import dto.BookInfo;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import model.Book;
import model.User;

@Stateless
@LocalBean
public class LibraryService {

    @PersistenceContext
    private EntityManager em;

    public List<BookInfo> listAllBooks() {
        return em.createQuery(
                "SELECT NEW dto.BookInfo(b.id, b.isbn, b.title, b.author, b.borrowed, b.borrowedBy, b.borrowedDate) " +
                        "FROM Book b " +
                        "ORDER BY b.id",
                BookInfo.class).getResultList();
    }

    public String processBookBorrow(String username, Long bookId) {
        Book book = em.find(Book.class, bookId);
        if (book == null) {
            return "Error: Book does not exist";
        }

        if (book.getBorrowed() != null && book.getBorrowed()) {
            return "Error: Book is not available (already borrowed by " + book.getBorrowedBy() + ")";
        }

        User user;
        try {
            user = em.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username",
                    User.class).setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return "Error: User does not exist";
        }

        book.setBorrowed(true);
        book.setBorrowedBy(username);
        book.setBorrowedDate(new Date());

        return "Success: Book '" + book.getTitle() + "' borrowed by " + username;
    }
}
