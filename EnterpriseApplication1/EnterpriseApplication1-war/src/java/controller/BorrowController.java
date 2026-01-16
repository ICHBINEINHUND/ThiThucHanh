package controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.BookManager;

@WebServlet(urlPatterns = { "/borrow" })
public class BorrowController extends HttpServlet {

    @EJB
    private BookManager bookManager;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("username");
        String bookParam = request.getParameter("bookId");

        String msg;

        if (user == null || user.trim().isEmpty() ||
                bookParam == null || bookParam.trim().isEmpty()) {
            msg = "Error: Please provide both username and book ID";
            request.setAttribute("msg", msg);
            request.setAttribute("type", "error");
            request.getRequestDispatcher("form.jsp").forward(request, response);
            return;
        }

        try {
            Long bookId = Long.parseLong(bookParam.trim());

            msg = bookManager.handleBorrow(user.trim(), bookId);

            String type = msg.startsWith("Success") ? "success" : "error";

            request.setAttribute("msg", msg);
            request.setAttribute("type", type);
            request.getRequestDispatcher("form.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            msg = "Error: Book ID must be a valid number";
            request.setAttribute("msg", msg);
            request.setAttribute("type", "error");
            request.getRequestDispatcher("form.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Borrow Controller";
    }
}
