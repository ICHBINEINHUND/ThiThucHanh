package servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.BookServiceBean;

@WebServlet(urlPatterns = { "/borrow" })
public class BorrowBookServlet extends HttpServlet {

    @EJB
    private BookServiceBean bookService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String bookIdStr = request.getParameter("bookId");

        String message;

        if (username == null || username.trim().isEmpty() ||
                bookIdStr == null || bookIdStr.trim().isEmpty()) {
            message = "Error: Please provide both username and book ID";
            request.setAttribute("message", message);
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("borrow_form.jsp").forward(request, response);
            return;
        }

        try {
            Long bookId = Long.parseLong(bookIdStr.trim());

            message = bookService.borrowBook(username.trim(), bookId);

            String messageType = message.startsWith("Success") ? "success" : "error";

            request.setAttribute("message", message);
            request.setAttribute("messageType", messageType);
            request.getRequestDispatcher("borrow_form.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            message = "Error: Book ID must be a valid number";
            request.setAttribute("message", message);
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("borrow_form.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("borrow_form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Borrow Book Servlet - Handles book borrowing requests";
    }
}
