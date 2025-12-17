package phu.wcd.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import phu.wcd.dao.MarkDAO;

import java.io.IOException;

@WebServlet("/delete-mark")
public class DeleteMarkServlet extends HttpServlet {

  private MarkDAO markDAO = new MarkDAO();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String markIdParam = req.getParameter("markId");
    String studentIdParam = req.getParameter("studentId");

    try {
      int markId = Integer.parseInt(markIdParam);
      int studentId = Integer.parseInt(studentIdParam);

      markDAO.delete(markId);

      resp.sendRedirect(req.getContextPath() + "/student-detail?studentId=" + studentId);
    } catch (NumberFormatException e) {
      resp.sendRedirect(req.getContextPath() + "/students");
    }
  }
}
