package phu.wcd.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import phu.wcd.entity.Student;
import phu.wcd.util.JPAUtil;

import java.util.List;

public class StudentDAO {

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
