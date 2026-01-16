package service;

import dto.BookDTO;
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
public class BookServiceBean {

    @PersistenceContext
    private EntityManager em;

    public List<BookDTO> getAllBooks() {
        return em.createQuery(
                "SELECT NEW dto.BookDTO(b.id, b.title, b.author, b.isBorrowed, b.borrowedBy, b.borrowedDate) " +
                        "FROM Book b " +
                        "ORDER BY b.id",
                BookDTO.class).getResultList();
    }

    public String borrowBook(String username, Long bookId) {

        Book book = em.find(Book.class, bookId);
        if (book == null) {
            return "Error: Book does not exist";
        }

        if (book.getIsBorrowed() != null && book.getIsBorrowed()) {
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

        book.setIsBorrowed(true);

        book.setBorrowedBy(username);
        book.setBorrowedDate(new Date());

        return "Success: Book '" + book.getTitle() + "' borrowed by " + username;
    }
}
