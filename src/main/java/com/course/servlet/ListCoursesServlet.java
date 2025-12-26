package com.course.servlet;

import com.course.dao.CourseDAO;
import com.course.entity.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet to list all courses
 */
public class ListCoursesServlet extends HttpServlet {
    
    private CourseDAO courseDAO = new CourseDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get all courses from database
        List<Course> courses = courseDAO.findAll();
        
        // Set courses as request attribute
        request.setAttribute("courses", courses);
        
        // Forward to list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }
}
