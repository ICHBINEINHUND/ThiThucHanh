package org.vak.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.vak.dao.StudentDAO;
import org.vak.entity.Student;

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
