package service;

import dto.BookItem;
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
public class BookManager {

    @PersistenceContext
    private EntityManager em;

    public List<BookItem> fetchAllBooks() {
        return em.createQuery(
                "SELECT NEW dto.BookItem(b.id, b.title, b.author, b.isAvailable, b.borrowedBy, b.borrowedDate) " +
                        "FROM Book b " +
                        "ORDER BY b.id",
                BookItem.class).getResultList();
    }

    public String handleBorrow(String username, Long bookId) {
        Book book = em.find(Book.class, bookId);
        if (book == null) {
            return "Error: Book does not exist";
        }

        if (book.getIsAvailable() == null || !book.getIsAvailable()) {
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

        book.setIsAvailable(false);
        book.setBorrowedBy(username);
        book.setBorrowedDate(new Date());

        return "Success: Book '" + book.getTitle() + "' borrowed by " + username;
    }
}
