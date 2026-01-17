package com.studentmanagement.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.studentmanagement.dao.MarkDAO;
import com.studentmanagement.dao.StudentDAO;
import com.studentmanagement.entity.Mark;
import com.studentmanagement.entity.Student;

import java.io.IOException;
import java.util.List;

@WebServlet("/student-detail")
public class StudentDetailServlet extends HttpServlet {

    private StudentDAO studentDAO = new StudentDAO();
    private MarkDAO markDAO = new MarkDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentIdParam = request.getParameter("studentId");

        if (studentIdParam == null || studentIdParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/students");
            return;
        }

        try {
            int studentId = Integer.parseInt(studentIdParam);
            Student student = studentDAO.findById(studentId);

            if (student == null) {
                response.sendRedirect(request.getContextPath() + "/students");
                return;
            }

            List<Mark> marks = markDAO.findByStudentId(studentId);

            request.setAttribute("student", student);
            request.setAttribute("marks", marks);
            request.getRequestDispatcher("/student-detail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/students");
        }
    }
}
