package com.example.courses.resource;

import com.example.courses.model.Course;
import com.example.courses.service.CourseService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/courses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseResource {

    @EJB
    private CourseService courseService;

    // 1. GET /courses - Get all courses
    @GET
    public Response getAllCourses() {
        List<Course> courses = courseService.getAll();
        return Response.ok(courses).build();
    }

    // 2. GET /courses/{id} - Get course by ID
    @GET
    @Path("/{id}")
    public Response getCourseById(@PathParam("id") int id) {
        Course course = courseService.getById(id);
        if (course == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Course not found\"}").build();
        }
        return Response.ok(course).build();
    }

    // 3. POST /courses - Create new course
    @POST
    public Response createCourse(Course course) {
        try {
            Course created = courseService.create(course);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

    // 4. PUT /courses/{id} - Update course
    @PUT
    @Path("/{id}")
    public Response updateCourse(@PathParam("id") int id, Course course) {
        Course updated = courseService.update(id, course);
        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Course not found\"}").build();
        }
        return Response.ok(updated).build();
    }

    // 5. DELETE /courses/{id} - Delete course
    @DELETE
    @Path("/{id}")
    public Response deleteCourse(@PathParam("id") int id) {
        boolean deleted = courseService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Course not found\"}").build();
        }
        return Response.ok("{\"message\": \"Course deleted successfully\"}").build();
    }

    // 6. GET /courses/search?title= - Search by title
    @GET
    @Path("/search")
    public Response searchByTitle(@QueryParam("title") String title) {
        if (title == null || title.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Title parameter is required\"}").build();
        }
        List<Course> courses = courseService.searchByTitle(title);
        return Response.ok(courses).build();
    }

    // 7. GET /courses/pagination?page=&limit= - Pagination
    @GET
    @Path("/pagination")
    public Response getPaginated(@QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("limit") @DefaultValue("10") int limit) {
        if (page < 1)
            page = 1;
        if (limit < 1)
            limit = 10;
        List<Course> courses = courseService.getPaginated(page, limit);
        return Response.ok(courses).build();
    }
}
