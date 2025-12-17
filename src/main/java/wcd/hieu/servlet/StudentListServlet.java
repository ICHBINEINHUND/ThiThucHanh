package wcd.hieu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wcd.hieu.dao.StudentDAO;
import wcd.hieu.entity.Student;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"", "/students"})
public class StudentListServlet extends HttpServlet {

  private StudentDAO student_dao = new StudentDAO();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<Student> students = student_dao.findAll();
    req.setAttribute("students", students);
    req.getRequestDispatcher("/student-list.jsp").forward(req, resp);
  }
}
