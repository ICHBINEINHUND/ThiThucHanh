package com.course.dao;

import com.course.entity.Course;

import javax.persistence.*;
import java.util.List;

/**
 * Data Access Object for Course entity
 * Handles all database operations using JPA
 */
public class CourseDAO {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("CoursePU");

    /**
     * Get all courses from database
     */
    public List<Course> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c ORDER BY c.id DESC", Course.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Save a new course to database
     */
    public void save(Course course) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(course);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Find course by ID
     */
    public Course findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Course.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Update existing course
     */
    public void update(Course course) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(course);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Delete course by ID
     */
    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Course course = em.find(Course.class, id);
            if (course != null) {
                em.remove(course);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Find course by course code (for duplicate check)
     */
    public Course findByCourseCode(String courseCode) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Course> query = em.createQuery(
                    "SELECT c FROM Course c WHERE c.courseCode = :code", Course.class);
            query.setParameter("code", courseCode);
            List<Course> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }

    /**
     * Close EntityManagerFactory when application shuts down
     */
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
