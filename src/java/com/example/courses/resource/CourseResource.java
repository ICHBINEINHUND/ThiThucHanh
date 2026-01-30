package com.example.courses.resource;
import com.example.courses.model.Course;
import com.example.courses.service.CourseService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
@Path("/courses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseResource {
  @Inject
  private CourseService courseService;
  @GET
  public Response getAll() {
    List<Course> courses = courseService.getAll();
    return Response.ok(courses).build();
  }
  @GET
  @Path("/{id}")
  public Response getById(@PathParam("id") int id) {
    Course course = courseService.getById(id);
    if (course == null) {
      return Response.status(Response.Status.NOT_FOUND)
        .entity("{\"error\": \"Course not found\"}").build();
    }
    return Response.ok(course).build();
  }
  @POST
  public Response create(Course course) {
    try {
      Course created = courseService.create(course);
      return Response.status(Response.Status.CREATED).entity(created).build();
    } catch (IllegalArgumentException e) {
      return Response.status(Response.Status.BAD_REQUEST)
        .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
    }
  }
  @PUT
  @Path("/{id}")
  public Response update(@PathParam("id") int id, Course course) {
    try {
      Course updated = courseService.update(id, course);
      if (updated == null) {
        return Response.status(Response.Status.NOT_FOUND)
          .entity("{\"error\": \"Course not found\"}").build();
      }
      return Response.ok(updated).build();
    } catch (IllegalArgumentException e) {
      return Response.status(Response.Status.BAD_REQUEST)
        .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
    }
  }
  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") int id) {
    boolean deleted = courseService.delete(id);
    if (!deleted) {
      return Response.status(Response.Status.NOT_FOUND)
        .entity("{\"error\": \"Course not found\"}").build();
    }
    return Response.ok("{\"message\": \"Course deleted successfully\"}").build();
  }
  @GET
  @Path("/search")
  public Response searchByCode(@QueryParam("code") String code) {
    if (code == null || code.trim().isEmpty()) {
      return Response.status(Response.Status.BAD_REQUEST)
        .entity("{\"error\": \"Code parameter is required\"}").build();
    }
    List<Course> courses = courseService.searchByCode(code);
    return Response.ok(courses).build();
  }
  @GET
  @Path("/pagination")
  public Response getPaginated(@QueryParam("page") @DefaultValue("1") int page,
                               @QueryParam("limit") @DefaultValue("10") int limit) {
    if (page < 1) page = 1;
    if (limit < 1) limit = 10;
    List<Course> courses = courseService.getPaginated(page, limit);
    return Response.ok(courses).build();
  }
}
