package com.course.servlet;

import com.course.dao.CourseDAO;
import com.course.entity.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to edit existing course
 */
public class EditCourseServlet extends HttpServlet {

    private CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get course ID from request
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/list");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            Course course = courseDAO.findById(id);

            if (course == null) {
                response.sendRedirect(request.getContextPath() + "/list");
                return;
            }

            // Set course as request attribute
            request.setAttribute("course", course);
            request.getRequestDispatcher("/edit.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set UTF-8 encoding for Vietnamese characters
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Get form parameters
        String idParam = request.getParameter("id");
        String courseCode = request.getParameter("courseCode");
        String courseName = request.getParameter("courseName");
        String description = request.getParameter("description");

        // Validation
        String errorMessage = null;
        int id = 0;

        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/list");
            return;
        }

        // Check if required fields are empty
        if (courseCode == null || courseCode.trim().isEmpty()) {
            errorMessage = "Course Code is required!";
        } else if (courseName == null || courseName.trim().isEmpty()) {
            errorMessage = "Course Name is required!";
        } else {
            // Check if course code already exists (excluding current course)
            Course existingCourse = courseDAO.findByCourseCode(courseCode.trim());
            if (existingCourse != null && existingCourse.getId() != id) {
                errorMessage = "Course Code already exists! Please use a different code.";
            }
        }

        // If validation fails, return to form with error
        if (errorMessage != null) {
            Course course = new Course();
            course.setId(id);
            course.setCourseCode(courseCode);
            course.setCourseName(courseName);
            course.setDescription(description);

            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("course", course);
            request.getRequestDispatcher("/edit.jsp").forward(request, response);
            return;
        }

        // Update course
        Course course = courseDAO.findById(id);
        if (course != null) {
            course.setCourseCode(courseCode.trim());
            course.setCourseName(courseName.trim());
            course.setDescription(description != null ? description.trim() : "");

            try {
                courseDAO.update(course);
                // Redirect to list page
                response.sendRedirect(request.getContextPath() + "/list");
            } catch (Exception e) {
                request.setAttribute("errorMessage", "Error updating course: " + e.getMessage());
                request.setAttribute("course", course);
                request.getRequestDispatcher("/edit.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/list");
        }
    }
}
