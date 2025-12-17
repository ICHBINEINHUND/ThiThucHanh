package org.vak.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.vak.entity.Student;
import org.vak.util.JPAUtil;

import java.util.List;

public class StudentDAO {

  public List<Student> findAll() {
    EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
    try {
      return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    } finally {
      em.close();
    }
  }

  public Student findById(int id) {
    EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
    try {
      return em.find(Student.class, id);
    } finally {
      em.close();
    }
  }
}
