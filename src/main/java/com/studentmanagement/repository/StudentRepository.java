package com.studentmanagement.repository;

import jakarta.persistence.EntityManager;
import com.studentmanagement.model.Student;
import com.studentmanagement.util.JPAUtil;

import java.util.List;

public class StudentRepository {

    public List<Student> findAll() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Student findById(int id) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return entityManager.find(Student.class, id);
        } finally {
            entityManager.close();
        }
    }
}
