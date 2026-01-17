package com.studentmanagement.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import com.studentmanagement.entity.Mark;
import com.studentmanagement.util.JPAUtil;

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
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(mark);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public void delete(int id) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Mark mark = entityManager.find(Mark.class, id);
            if (mark != null) {
                entityManager.remove(mark);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
