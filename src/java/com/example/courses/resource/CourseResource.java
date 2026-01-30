package com.example.courses.resource;

import com.example.courses.model.Course;
import com.example.courses.service.CourseService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST Resource for Course management
 * Base path: /api/courses
 */
@Path("/courses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseResource {

    @EJB
    private CourseService courseService;

    /**
     * GET /api/courses - Get all courses
     */
    @GET
    public Response getAllCourses() {
        try {
            List<Course> courses = courseService.getAll();
            return Response.ok(courses).build();
        } catch (Exception e) {
            return createErrorResponse(500, "Error retrieving courses: " + e.getMessage());
        }
    }

    /**
     * GET /api/courses/{id} - Get course by ID
     */
    @GET
    @Path("/{id}")
    public Response getCourseById(@PathParam("id") int id) {
        try {
            Course course = courseService.getById(id);
            if (course == null) {
                return createErrorResponse(404, "Course not found with id: " + id);
            }
            return Response.ok(course).build();
        } catch (Exception e) {
            return createErrorResponse(500, "Error retrieving course: " + e.getMessage());
        }
    }

    /**
     * POST /api/courses - Create new course
     */
    @POST
    public Response createCourse(Course course) {
        try {
            Course created = courseService.create(course);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (IllegalArgumentException e) {
            return createErrorResponse(400, "Validation error: " + e.getMessage());
        } catch (Exception e) {
            return createErrorResponse(500, "Error creating course: " + e.getMessage());
        }
    }

    /**
     * PUT /api/courses/{id} - Update existing course
     */
    @PUT
    @Path("/{id}")
    public Response updateCourse(@PathParam("id") int id, Course course) {
        try {
            Course updated = courseService.update(id, course);
            if (updated == null) {
                return createErrorResponse(404, "Course not found with id: " + id);
            }
            return Response.ok(updated).build();
        } catch (IllegalArgumentException e) {
            return createErrorResponse(400, "Validation error: " + e.getMessage());
        } catch (Exception e) {
            return createErrorResponse(500, "Error updating course: " + e.getMessage());
        }
    }

    /**
     * DELETE /api/courses/{id} - Delete course
     */
    @DELETE
    @Path("/{id}")
    public Response deleteCourse(@PathParam("id") int id) {
        try {
            boolean deleted = courseService.delete(id);
            if (!deleted) {
                return createErrorResponse(404, "Course not found with id: " + id);
            }
            Map<String, String> response = new HashMap<>();
            response.put("message", "Course deleted successfully");
            return Response.ok(response).build();
        } catch (Exception e) {
            return createErrorResponse(500, "Error deleting course: " + e.getMessage());
        }
    }

    /**
     * GET /api/courses/search?title={keyword} - Search courses by title
     */
    @GET
    @Path("/search")
    public Response searchCourses(@QueryParam("title") String title) {
        try {
            if (title == null || title.trim().isEmpty()) {
                return createErrorResponse(400, "Search parameter 'title' is required");
            }
            List<Course> courses = courseService.searchByTitle(title);
            return Response.ok(courses).build();
        } catch (Exception e) {
            return createErrorResponse(500, "Error searching courses: " + e.getMessage());
        }
    }

    /**
     * GET /api/courses/pagination?page={page}&limit={limit} - Get paginated courses
     */
    @GET
    @Path("/pagination")
    public Response getPaginatedCourses(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("limit") @DefaultValue("10") int limit) {
        try {
            if (page < 1) {
                return createErrorResponse(400, "Page must be >= 1");
            }
            if (limit < 1) {
                return createErrorResponse(400, "Limit must be >= 1");
            }
            List<Course> courses = courseService.getPaginated(page, limit);

            Map<String, Object> response = new HashMap<>();
            response.put("page", page);
            response.put("limit", limit);
            response.put("data", courses);

            return Response.ok(response).build();
        } catch (Exception e) {
            return createErrorResponse(500, "Error retrieving paginated courses: " + e.getMessage());
        }
    }

    /**
     * Helper method to create error responses
     */
    private Response createErrorResponse(int status, String message) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", true);
        error.put("message", message);
        error.put("status", status);
        return Response.status(status).entity(error).build();
    }
}
