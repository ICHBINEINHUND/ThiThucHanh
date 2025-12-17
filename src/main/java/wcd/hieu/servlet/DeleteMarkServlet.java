package wcd.hieu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wcd.hieu.dao.MarkDAO;

import java.io.IOException;

@WebServlet("/delete-mark")
public class DeleteMarkServlet extends HttpServlet {

  private MarkDAO mark_dao = new MarkDAO();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String mark_id_param = req.getParameter("markId");
    String student_id_param = req.getParameter("studentId");

    try {
      int mark_id = Integer.parseInt(mark_id_param);
      int student_id = Integer.parseInt(student_id_param);

      mark_dao.delete(mark_id);

      resp.sendRedirect(req.getContextPath() + "/student-detail?studentId=" + student_id);
    } catch (NumberFormatException e) {
      resp.sendRedirect(req.getContextPath() + "/students");
    }
  }
}
