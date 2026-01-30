package com.example.courses.service;
import com.example.courses.model.Course;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
@Stateless
public class CourseService {
  @PersistenceContext(unitName = "CoursesPU")
  private EntityManager em;
  public List<Course> getAll() {
    TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c", Course.class);
    return query.getResultList();
  }
  public Course getById(int id) {
    return em.find(Course.class, id);
  }
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
  public Course update(int id, Course course) {
    Course existing = em.find(Course.class, id);
    if (existing == null) {
      return null;
    }
    if (course.getCode() == null || course.getCode().trim().isEmpty()) {
      throw new IllegalArgumentException("Code must not be empty");
    }
    if (course.getCredit() <= 0) {
      throw new IllegalArgumentException("Credit must be greater than 0");
    }
    existing.setCode(course.getCode());
    existing.setTitle(course.getTitle());
    existing.setCredit(course.getCredit());
    return em.merge(existing);
  }
  public boolean delete(int id) {
    Course existing = em.find(Course.class, id);
    if (existing == null) {
      return false;
    }
    em.remove(existing);
    return true;
  }
  public List<Course> searchByCode(String keyword) {
    TypedQuery<Course> query = em.createQuery(
      "SELECT c FROM Course c WHERE LOWER(c.code) LIKE LOWER(:keyword)", Course.class);
    query.setParameter("keyword", "%" + keyword + "%");
    return query.getResultList();
  }
  public List<Course> getPaginated(int page, int limit) {
    TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c", Course.class);
    query.setFirstResult((page - 1) * limit);
    query.setMaxResults(limit);
    return query.getResultList();
  }
}
