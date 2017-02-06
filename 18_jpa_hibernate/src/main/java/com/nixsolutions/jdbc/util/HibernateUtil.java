package com.nixsolutions.jdbc.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
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
      
      String urlPattern = configuration.getProperty("hibernate.connection.url");
      String schema = configuration.getProperty("db.schema");
      String url = HibernateUtil.class.getClassLoader().getResource(schema + ".mv.db").toString();
      
      configuration.setProperty("hibernate.connection.url", 
          String.format(urlPattern, url.substring(0, url.lastIndexOf(schema) + schema.length())));
      
      
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
  
  public static void closeQuietly(Session ses) {
    try {
      ses.close();
    } catch (Exception ex) {
      
    }
  }
}
