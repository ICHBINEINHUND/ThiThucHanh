package wcd.hieu.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import wcd.hieu.entity.Mark;
import wcd.hieu.util.JPAUtil;

import java.util.List;

public class MarkDAO {

  public List<Mark> findByStudentId(int student_id) {
    EntityManager entity_manager = JPAUtil.getEntityManagerFactory().createEntityManager();
    try {
      return entity_manager.createQuery("SELECT m FROM Mark m WHERE m.student.id = :studentId", Mark.class)
                .setParameter("studentId", student_id)
                .getResultList();
    } finally {
      entity_manager.close();
    }
  }

  public Mark findById(int id) {
    EntityManager entity_manager = JPAUtil.getEntityManagerFactory().createEntityManager();
    try {
      return entity_manager.find(Mark.class, id);
    } finally {
      entity_manager.close();
    }
  }

  public void update(Mark mark) {
    EntityManager entity_manager = JPAUtil.getEntityManagerFactory().createEntityManager();
    EntityTransaction tx = null;
    try {
      tx = entity_manager.getTransaction();
      tx.begin();
      entity_manager.merge(mark);
      tx.commit();
    } catch (Exception e) {
      if (tx != null && tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      entity_manager.close();
    }
  }

  public void delete(int id) {
    EntityManager entity_manager = JPAUtil.getEntityManagerFactory().createEntityManager();
    EntityTransaction tx = null;
    try {
      tx = entity_manager.getTransaction();
      tx.begin();
      Mark mark = entity_manager.find(Mark.class, id);
      if (mark != null) {
        entity_manager.remove(mark);
      }
      tx.commit();
    } catch (Exception e) {
      if (tx != null && tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      entity_manager.close();
    }
  }
}
