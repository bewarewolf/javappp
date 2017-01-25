package com.nixsolutions.jdbc.util;

import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

  private static final SessionFactory sessionFactory;
  private static final Logger LOG = LogManager.getLogger();
  private static final ServiceRegistry serviceRegistry;

  static {
    try {
      Configuration configuration = new Configuration();
      
      configuration.configure();
      
      String url = configuration.getProperty("hibernate.connection.url");
      LOG.info(url);
      URL db = HibernateUtil.class.getClassLoader().getResource("sqllab.mv.db"); 
      LOG.info(db);
      configuration.setProperty("hibernate.connection.url", 
          String.format(url, db.toString().replace(".mv.db", "")));
      
      
      serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
      sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    } catch (Throwable ex) {
      LOG.error("Initial SessionFactory creation failed.", ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}
