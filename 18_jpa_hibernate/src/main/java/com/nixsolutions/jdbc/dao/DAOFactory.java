package com.nixsolutions.jdbc.dao;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.jdbc.dao.impl.DAOFactoryImpl;

public abstract class DAOFactory {

  private static final Logger LOG = LogManager.getLogger();
  
  public static DAOFactory getFactory() throws Exception {
     
        return new DAOFactoryImpl();
    
  }
  
  public abstract GradeDAO getGradeDAO();
  
  public abstract JournalDAO getJournalDAO();
  
  public abstract PersonDAO getPersonDAO();
  
  public abstract PersonStatusDAO getPersonStatusDAO();
  
  public abstract PersonTypeDAO getPersonTypeDAO();
  
  public abstract PhoneNumberDAO getPhoneNumberDAO();
  
  public abstract SemesterDAO getSemesterDAO();
  
  public abstract SubjectDAO getSubjectDAO();
}
