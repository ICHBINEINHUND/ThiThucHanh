package org.vak.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
  private static final EntityManagerFactory emf;

  static {
    try {
      emf = Persistence.createEntityManagerFactory("MarkPU");
    } catch (Throwable ex) {
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static EntityManagerFactory getEntityManagerFactory() {
    return emf;
  }
}