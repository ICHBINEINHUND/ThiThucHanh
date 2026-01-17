package com.studentmanagement.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.studentmanagement.dao.MarkDAO;

import java.io.IOException;

@WebServlet("/delete-mark")
public class DeleteMarkServlet extends HttpServlet {

    private MarkDAO markDAO = new MarkDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String markIdParam = request.getParameter("markId");
        String studentIdParam = request.getParameter("studentId");

        try {
            int markId = Integer.parseInt(markIdParam);
            int studentId = Integer.parseInt(studentIdParam);

            markDAO.delete(markId);

            response.sendRedirect(request.getContextPath() + "/student-detail?studentId=" + studentId);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/students");
        }
    }
}
