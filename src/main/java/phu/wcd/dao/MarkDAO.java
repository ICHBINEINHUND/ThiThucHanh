package phu.wcd.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import phu.wcd.entity.Mark;
import phu.wcd.util.JPAUtil;

import java.util.List;

public class MarkDAO {

  public List<Mark> findByStudentId(int studentId) {
    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    try {
      return entityManager.createQuery("SELECT m FROM Mark m WHERE m.student.id = :studentId", Mark.class)
                .setParameter("studentId", studentId)
                .getResultList();
    } finally {
      entityManager.close();
    }
  }

  public Mark findById(int id) {
    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    try {
      return entityManager.find(Mark.class, id);
    } finally {
      entityManager.close();
    }
  }

  public void update(Mark mark) {
    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    EntityTransaction tx = null;
    try {
      tx = entityManager.getTransaction();
      tx.begin();
      entityManager.merge(mark);
      tx.commit();
    } catch (Exception e) {
      if (tx != null && tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      entityManager.close();
    }
  }

  public void delete(int id) {
    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    EntityTransaction tx = null;
    try {
      tx = entityManager.getTransaction();
      tx.begin();
      Mark mark = entityManager.find(Mark.class, id);
      if (mark != null) {
        entityManager.remove(mark);
      }
      tx.commit();
    } catch (Exception e) {
      if (tx != null && tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      entityManager.close();
    }
  }
}
