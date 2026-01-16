package servlet;

import dto.BookDTO;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import service.BookServiceBean;

@WebServlet(urlPatterns = { "/books" })
public class BookListServlet extends HttpServlet {

    @EJB
    private BookServiceBean bookService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<BookDTO> bookList = bookService.getAllBooks();

        request.setAttribute("bookList", bookList);
        request.getRequestDispatcher("book_list.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Book List Servlet - Displays all books with status";
    }
}
