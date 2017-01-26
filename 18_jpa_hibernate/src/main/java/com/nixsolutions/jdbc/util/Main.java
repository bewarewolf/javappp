package com.nixsolutions.jdbc.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nixsolutions.jdbc.bean.Person;

public class Main {

  private static final Logger LOG = LogManager.getLogger();
  
  public static void main(String[] args) throws Exception {
    LOG.info("Started");
    
    SessionFactory fact = HibernateUtil.getSessionFactory();
    Session ses = fact.openSession();
        
    Person p = (Person) ses.get(Person.class, new Integer(8));
    LOG.info(p);
    
    ses.close();
    fact.close();
  }

}
