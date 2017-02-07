package com.nixsolutions.jdbc.util;

import org.hibernate.Session;

public class HibernateUtil {
  
  public static void closeQuietly(Session ses) {
    try {
      ses.close();
    } catch (Exception ex) {
      
    }
  }
}
