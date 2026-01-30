package com.example.courses.service;

import com.example.courses.model.Course;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CourseService {

    @PersistenceContext(unitName = "WS_Lab321PU")
    private EntityManager em;

    // Get all courses
    public List<Course> getAll() {
        return em.createNamedQuery("Course.findAll", Course.class).getResultList();
    }

    // Get course by ID
    public Course getById(int id) {
        return em.find(Course.class, id);
    }

    // Create new course
    public Course create(Course course) {
        if (course.getCode() == null || course.getCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Code must not be empty");
        }
        if (course.getCredit() <= 0) {
            throw new IllegalArgumentException("Credit must be greater than 0");
        }
        em.persist(course);
        return course;
    }

    // Update course
    public Course update(int id, Course course) {
        Course existing = em.find(Course.class, id);
        if (existing == null) {
            return null;
        }
        existing.setCode(course.getCode());
        existing.setTitle(course.getTitle());
        existing.setCredit(course.getCredit());
        return em.merge(existing);
    }

    // Delete course
    public boolean delete(int id) {
        Course course = em.find(Course.class, id);
        if (course == null) {
            return false;
        }
        em.remove(course);
        return true;
    }

    // Search by title (case-insensitive)
    public List<Course> searchByTitle(String keyword) {
        TypedQuery<Course> query = em.createNamedQuery("Course.searchByTitle", Course.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }

    // Get paginated courses
    public List<Course> getPaginated(int page, int limit) {
        TypedQuery<Course> query = em.createNamedQuery("Course.findAll", Course.class);
        query.setFirstResult((page - 1) * limit);
        query.setMaxResults(limit);
        return query.getResultList();
    }
}
