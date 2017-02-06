package com.nixsolutions.jdbc.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.jdbc.bean.Grade;
import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.GradeDAO;

public class Main {

  private static final Logger LOG = LogManager.getLogger();
  
  public static void main(String[] args) throws Exception {
    SessionFactory fact = null;
    Session ses = null;
    Transaction tx = null;
    
    try {
      fact = HibernateUtil.getSessionFactory();
      ses = fact.openSession();
      tx = ses.beginTransaction();      
      
      SQLQuery q = ses.createSQLQuery("alter table phone_number alter column person_id set null");
      q.executeUpdate();
      
      tx.commit();
      
    } catch (Exception ex) {
      LOG.error(ex);
      tx.rollback();
      throw ex;
    } finally {           
      HibernateUtil.closeQuietly(ses);
    }
  }

}
