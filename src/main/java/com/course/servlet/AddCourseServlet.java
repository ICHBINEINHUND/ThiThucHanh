package com.course.servlet;

import com.course.dao.CourseDAO;
import com.course.entity.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to add new course
 */
public class AddCourseServlet extends HttpServlet {

    private CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Display add form
        request.getRequestDispatcher("/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set UTF-8 encoding for Vietnamese characters
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Get form parameters
        String courseCode = request.getParameter("courseCode");
        String courseName = request.getParameter("courseName");
        String description = request.getParameter("description");

        // Validation
        String errorMessage = null;

        // Check if required fields are empty
        if (courseCode == null || courseCode.trim().isEmpty()) {
            errorMessage = "Course Code is required!";
        } else if (courseName == null || courseName.trim().isEmpty()) {
            errorMessage = "Course Name is required!";
        } else {
            // Check if course code already exists
            Course existingCourse = courseDAO.findByCourseCode(courseCode.trim());
            if (existingCourse != null) {
                errorMessage = "Course Code already exists! Please use a different code.";
            }
        }

        // If validation fails, return to form with error
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("courseCode", courseCode);
            request.setAttribute("courseName", courseName);
            request.setAttribute("description", description);
            request.getRequestDispatcher("/add.jsp").forward(request, response);
            return;
        }

        // Create and save new course
        Course course = new Course();
        course.setCourseCode(courseCode.trim());
        course.setCourseName(courseName.trim());
        course.setDescription(description != null ? description.trim() : "");

        try {
            courseDAO.save(course);
            // Redirect to list page
            response.sendRedirect(request.getContextPath() + "/list");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error saving course: " + e.getMessage());
            request.setAttribute("courseCode", courseCode);
            request.setAttribute("courseName", courseName);
            request.setAttribute("description", description);
            request.getRequestDispatcher("/add.jsp").forward(request, response);
        }
    }
}
