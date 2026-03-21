package com.example.students.service;

import com.example.students.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class StudentService {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPU");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Student> getAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Student getById(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }

    public void create(Student s) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public boolean update(int id, Student s) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Student existing = em.find(Student.class, id);
            if (existing != null) {
                existing.setName(s.getName());
                existing.setAge(s.getAge());
                existing.setMajor(s.getMajor());
                em.getTransaction().commit();
                return true;
            }
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean delete(int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Student existing = em.find(Student.class, id);
            if (existing != null) {
                em.remove(existing);
                em.getTransaction().commit();
                return true;
            }
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public List<Student> searchByName(String keyword) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                "SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(:keyword)", Student.class);
            query.setParameter("keyword", "%" + keyword + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Student> getPaginated(int page, int limit) {
        if (page < 1) page = 1;
        if (limit < 1) limit = 10;
        
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s ORDER BY s.id ASC", Student.class);
            query.setFirstResult((page - 1) * limit);
            query.setMaxResults(limit);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
