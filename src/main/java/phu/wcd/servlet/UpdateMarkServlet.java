package phu.wcd.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import phu.wcd.dao.MarkDAO;
import phu.wcd.entity.Mark;

import java.io.IOException;

@WebServlet("/update-mark")
public class UpdateMarkServlet extends HttpServlet {

  private MarkDAO markDAO = new MarkDAO();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String markIdParam = req.getParameter("markId");
    String scoreValueParam = req.getParameter("scoreValue");
    String studentIdParam = req.getParameter("studentId");

    try {
      int markId = Integer.parseInt(markIdParam);
      float scoreValue = Float.parseFloat(scoreValueParam);
      int studentId = Integer.parseInt(studentIdParam);

      if (scoreValue < 0 || scoreValue > 100) {
        resp.sendRedirect(req.getContextPath() + "/student-detail?studentId=" + studentId + "&error=invalid_score");
        return;
      }

      Mark mark = markDAO.findById(markId);
      if (mark != null) {
        mark.setScoreValue(scoreValue);
        markDAO.update(mark);
      }

      resp.sendRedirect(req.getContextPath() + "/student-detail?studentId=" + studentId);
    } catch (NumberFormatException e) {
      resp.sendRedirect(req.getContextPath() + "/students");
    }
  }
}
