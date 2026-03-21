package com.example.students.resource;

import com.example.students.model.Student;
import com.example.students.service.StudentService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

    private final StudentService studentService = new StudentService();

    @GET
    public Response getAllStudents() {
        List<Student> list = studentService.getAll();
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id: \\d+}")
    public Response getStudent(@PathParam("id") int id) {
        Student student = studentService.getById(id);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(student).build();
    }

    @GET
    @Path("/search")
    public Response searchStudents(@QueryParam("name") String name) {
        if (name == null || name.trim().isEmpty()) {
            return Response.ok(studentService.getAll()).build();
        }
        List<Student> list = studentService.searchByName(name);
        return Response.ok(list).build();
    }

    @GET
    @Path("/pagination")
    public Response getPaginatedStudents(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("limit") @DefaultValue("5") int limit) {
        List<Student> list = studentService.getPaginated(page, limit);
        return Response.ok(list).build();
    }

    @POST
    public Response createStudent(Student student) {
        if (!isValid(student)) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"error\":\"Invalid input: name and major must not be empty, and age must be >= 18.\"}")
                           .build();
        }
        
        studentService.create(student);
        return Response.status(Response.Status.CREATED).entity(student).build();
    }

    @PUT
    @Path("/{id: \\d+}")
    public Response updateStudent(@PathParam("id") int id, Student student) {
        if (!isValid(student)) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"error\":\"Invalid input: name and major must not be empty, and age must be >= 18.\"}")
                           .build();
        }
        
        boolean updated = studentService.update(id, student);
        if (!updated) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(student).build();
    }

    @DELETE
    @Path("/{id: \\d+}")
    public Response deleteStudent(@PathParam("id") int id) {
        boolean deleted = studentService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
    
    // Validation helper
    private boolean isValid(Student student) {
        if (student == null) return false;
        if (student.getName() == null || student.getName().trim().isEmpty()) return false;
        if (student.getMajor() == null || student.getMajor().trim().isEmpty()) return false;
        if (student.getAge() < 18) return false;
        return true;
    }
}
