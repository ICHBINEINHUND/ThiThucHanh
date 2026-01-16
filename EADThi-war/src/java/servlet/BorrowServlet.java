package servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.LibraryService;

@WebServlet(urlPatterns = { "/borrow" })
public class BorrowServlet extends HttpServlet {

    @EJB
    private LibraryService libraryService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("username");
        String bookIdParam = request.getParameter("bookId");

        String resultMessage;

        if (userName == null || userName.trim().isEmpty() ||
                bookIdParam == null || bookIdParam.trim().isEmpty()) {
            resultMessage = "Error: Please provide both username and book ID";
            request.setAttribute("resultMessage", resultMessage);
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("borrow.jsp").forward(request, response);
            return;
        }

        try {
            Long bookId = Long.parseLong(bookIdParam.trim());

            resultMessage = libraryService.processBookBorrow(userName.trim(), bookId);

            String messageType = resultMessage.startsWith("Success") ? "success" : "error";

            request.setAttribute("resultMessage", resultMessage);
            request.setAttribute("messageType", messageType);
            request.getRequestDispatcher("borrow.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            resultMessage = "Error: Book ID must be a valid number";
            request.setAttribute("resultMessage", resultMessage);
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("borrow.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("borrow.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Borrow Servlet";
    }
}
