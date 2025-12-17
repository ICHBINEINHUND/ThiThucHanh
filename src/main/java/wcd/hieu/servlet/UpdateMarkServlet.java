package wcd.hieu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wcd.hieu.dao.MarkDAO;
import wcd.hieu.entity.Mark;

import java.io.IOException;

@WebServlet("/update-mark")
public class UpdateMarkServlet extends HttpServlet {

  private MarkDAO mark_dao = new MarkDAO();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String mark_id_param = req.getParameter("markId");
    String score_value_param = req.getParameter("scoreValue");
    String student_id_param = req.getParameter("studentId");

    try {
      int mark_id = Integer.parseInt(mark_id_param);
      float score_value = Float.parseFloat(score_value_param);
      int student_id = Integer.parseInt(student_id_param);

      if (score_value < 0 || score_value > 100) {
        resp.sendRedirect(req.getContextPath() + "/student-detail?studentId=" + student_id + "&error=invalid_score");
        return;
      }

      Mark mark = mark_dao.findById(mark_id);
      if (mark != null) {
        mark.setScore_value(score_value);
        mark_dao.update(mark);
      }

      resp.sendRedirect(req.getContextPath() + "/student-detail?studentId=" + student_id);
    } catch (NumberFormatException e) {
      resp.sendRedirect(req.getContextPath() + "/students");
    }
  }
}
