package com.nixsolutions.jdbc.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.jdbc.bean.Grade;
import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.bean.Subject;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.GradeDAO;
import com.nixsolutions.jdbc.dao.SubjectDAO;

public class Main {

  private static final Logger LOG = LogManager.getLogger();
  
  public static void main(String[] args) throws Exception {
    
    try {
      SubjectDAO p = DAOFactory.getFactory().getSubjectDAO();
      Subject s = p.getById(1);
      System.out.println(s);
      p.delete(1);
      System.out.println(s);
    } catch (Exception ex) {
      LOG.error(ex);
    
      throw ex;
    } finally {           
    
    }
  }

}
