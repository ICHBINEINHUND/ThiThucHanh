package wcd.hieu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wcd.hieu.dao.MarkDAO;
import wcd.hieu.dao.StudentDAO;
import wcd.hieu.entity.Mark;
import wcd.hieu.entity.Student;

import java.io.IOException;
import java.util.List;

@WebServlet("/student-detail")
public class StudentDetailServlet extends HttpServlet {

  private StudentDAO student_dao = new StudentDAO();
  private MarkDAO mark_dao = new MarkDAO();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String student_id_param = req.getParameter("studentId");
    
    if (student_id_param == null || student_id_param.isEmpty()) {
      resp.sendRedirect(req.getContextPath() + "/students");
      return;
    }

    try {
      int student_id = Integer.parseInt(student_id_param);
      Student student = student_dao.findById(student_id);
      
      if (student == null) {
        resp.sendRedirect(req.getContextPath() + "/students");
        return;
      }

      List<Mark> marks = mark_dao.findByStudentId(student_id);
      
      req.setAttribute("student", student);
      req.setAttribute("marks", marks);
      req.getRequestDispatcher("/student-detail.jsp").forward(req, resp);
    } catch (NumberFormatException e) {
      resp.sendRedirect(req.getContextPath() + "/students");
    }
  }
}
