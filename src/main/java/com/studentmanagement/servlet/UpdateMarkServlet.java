package com.studentmanagement.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.studentmanagement.dao.MarkDAO;
import com.studentmanagement.entity.Mark;

import java.io.IOException;

@WebServlet("/update-mark")
public class UpdateMarkServlet extends HttpServlet {

    private MarkDAO markDAO = new MarkDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String markIdParam = request.getParameter("markId");
        String scoreValueParam = request.getParameter("scoreValue");
        String studentIdParam = request.getParameter("studentId");

        try {
            int markId = Integer.parseInt(markIdParam);
            float scoreValue = Float.parseFloat(scoreValueParam);
            int studentId = Integer.parseInt(studentIdParam);

            if (scoreValue < 0 || scoreValue > 100) {
                response.sendRedirect(
                        request.getContextPath() + "/student-detail?studentId=" + studentId + "&error=invalid_score");
                return;
            }

            Mark mark = markDAO.findById(markId);
            if (mark != null) {
                mark.setScoreValue(scoreValue);
                markDAO.update(mark);
            }

            response.sendRedirect(request.getContextPath() + "/student-detail?studentId=" + studentId);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/students");
        }
    }
}
