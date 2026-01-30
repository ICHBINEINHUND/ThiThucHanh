package com.example.courses.service;

import com.example.courses.model.Course;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Service layer for Course CRUD operations using JPA
 */
@Stateless
public class CourseService {

    @PersistenceContext(unitName = "CoursesPU")
    private EntityManager em;

    /**
     * Get all courses
     */
    public List<Course> getAll() {
        return em.createQuery("SELECT c FROM Course c", Course.class)
                .getResultList();
    }

    /**
     * Get course by ID
     */
    public Course getById(int id) {
        return em.find(Course.class, id);
    }

    /**
     * Create a new course with validation
     */
    public Course create(Course course) {
        // Validate code is not empty
        if (course.getCode() == null || course.getCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Code must not be empty");
        }

        // Validate credit > 0
        if (course.getCredit() <= 0) {
            throw new IllegalArgumentException("Credit must be greater than 0");
        }

        em.persist(course);
        em.flush();
        return course;
    }

    /**
     * Update existing course with validation
     */
    public Course update(int id, Course courseData) {
        Course existing = em.find(Course.class, id);
        if (existing == null) {
            return null;
        }

        // Validate code is not empty
        if (courseData.getCode() == null || courseData.getCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Code must not be empty");
        }

        // Validate credit > 0
        if (courseData.getCredit() <= 0) {
            throw new IllegalArgumentException("Credit must be greater than 0");
        }

        existing.setCode(courseData.getCode());
        existing.setTitle(courseData.getTitle());
        existing.setCredit(courseData.getCredit());

        em.merge(existing);
        em.flush();
        return existing;
    }

    /**
     * Delete course by ID
     */
    public boolean delete(int id) {
        Course course = em.find(Course.class, id);
        if (course == null) {
            return false;
        }
        em.remove(course);
        em.flush();
        return true;
    }

    /**
     * Search courses by title (case-insensitive)
     */
    public List<Course> searchByTitle(String keyword) {
        String jpql = "SELECT c FROM Course c WHERE LOWER(c.title) LIKE LOWER(:keyword)";
        TypedQuery<Course> query = em.createQuery(jpql, Course.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }

    /**
     * Get paginated courses
     */
    public List<Course> getPaginated(int page, int limit) {
        int offset = (page - 1) * limit;
        return em.createQuery("SELECT c FROM Course c", Course.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
