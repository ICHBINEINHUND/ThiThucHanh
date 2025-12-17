package phu.wcd.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import phu.wcd.dao.StudentDAO;
import phu.wcd.entity.Student;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"", "/students"})
public class StudentListServlet extends HttpServlet {

  private StudentDAO studentDAO = new StudentDAO();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<Student> students = studentDAO.findAll();
    req.setAttribute("students", students);
    req.getRequestDispatcher("/student-list.jsp").forward(req, resp);
  }
}
