package com.nixsolutions.jdbc.util;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nixsolutions.jdbc.bean.Grade;
import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.bean.PersonType;

public class Main {

  private static final Logger LOG = LogManager.getLogger();
  
  public static void main(String[] args) throws Exception {
    LOG.info("Started");
    
    SessionFactory fact = HibernateUtil.getSessionFactory();
    Session ses = fact.openSession();
        
    List<Person> list3 = (List<Person>) ses.createCriteria(Person.class).list();
    for (Person g : list3) {
      LOG.info(g);
    }
  }

}
