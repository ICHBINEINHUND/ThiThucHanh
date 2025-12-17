package wcd.hieu.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import wcd.hieu.entity.Student;
import wcd.hieu.util.JPAUtil;

import java.util.List;

public class StudentDAO {

  public List<Student> findAll() {
    EntityManager entity_manager = JPAUtil.getEntityManagerFactory().createEntityManager();
    try {
      return entity_manager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    } finally {
      entity_manager.close();
    }
  }

  public Student findById(int id) {
    EntityManager entity_manager = JPAUtil.getEntityManagerFactory().createEntityManager();
    try {
      return entity_manager.find(Student.class, id);
    } finally {
      entity_manager.close();
    }
  }
}
