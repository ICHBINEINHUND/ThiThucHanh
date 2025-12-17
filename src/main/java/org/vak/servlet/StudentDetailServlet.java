package org.vak.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.vak.dao.MarkDAO;
import org.vak.dao.StudentDAO;
import org.vak.entity.Mark;
import org.vak.entity.Student;

import java.io.IOException;
import java.util.List;

@WebServlet("/student-detail")
public class StudentDetailServlet extends HttpServlet {

  private StudentDAO studentDAO = new StudentDAO();
  private MarkDAO markDAO = new MarkDAO();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String studentIdParam = req.getParameter("studentId");
    
    if (studentIdParam == null || studentIdParam.isEmpty()) {
      resp.sendRedirect(req.getContextPath() + "/students");
      return;
    }

    try {
      int studentId = Integer.parseInt(studentIdParam);
      Student student = studentDAO.findById(studentId);
      
      if (student == null) {
        resp.sendRedirect(req.getContextPath() + "/students");
        return;
      }

      List<Mark> marks = markDAO.findByStudentId(studentId);
      
      req.setAttribute("student", student);
      req.setAttribute("marks", marks);
      req.getRequestDispatcher("/student-detail.jsp").forward(req, resp);
    } catch (NumberFormatException e) {
      resp.sendRedirect(req.getContextPath() + "/students");
    }
  }
}
