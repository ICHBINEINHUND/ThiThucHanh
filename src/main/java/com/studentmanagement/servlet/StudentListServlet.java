package com.studentmanagement.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.model.Student;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "", "/students" })
public class StudentListServlet extends HttpServlet {

    private StudentRepository studentRepository = new StudentRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> students = studentRepository.findAll();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/student-list.jsp").forward(request, response);
    }
}
