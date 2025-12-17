package org.vak.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.vak.entity.Mark;
import org.vak.util.JPAUtil;

import java.util.List;

public class MarkDAO {

  public List<Mark> findByStudentId(int studentId) {
    EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
    try {
      return em.createQuery("SELECT m FROM Mark m WHERE m.student.id = :studentId", Mark.class)
                .setParameter("studentId", studentId)
                .getResultList();
    } finally {
      em.close();
    }
  }

  public Mark findById(int id) {
    EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
    try {
      return em.find(Mark.class, id);
    } finally {
      em.close();
    }
  }

  public void update(Mark mark) {
    EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
    EntityTransaction tx = null;
    try {
      tx = em.getTransaction();
      tx.begin();
      em.merge(mark);
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

  public void delete(int id) {
    EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
    EntityTransaction tx = null;
    try {
      tx = em.getTransaction();
      tx.begin();
      Mark mark = em.find(Mark.class, id);
      if (mark != null) {
        em.remove(mark);
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
}
