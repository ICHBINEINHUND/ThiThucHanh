package com.course.servlet;

import com.course.dao.CourseDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to delete a course
 */
public class DeleteCourseServlet extends HttpServlet {

    private CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get course ID from request
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                courseDAO.delete(id);
            } catch (NumberFormatException e) {
                // Invalid ID, just redirect
            }
        }

        // Redirect to list page
        response.sendRedirect(request.getContextPath() + "/list");
    }
}
