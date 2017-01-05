package com.nixsolutions.jdbc.dao;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.jdbc.dao.impl.h2.H2DAOFactory;

public abstract class DAOFactory {

  private static final Logger LOG = LogManager.getLogger();
  
  public static DAOFactory getFactory() throws Exception {
    try (FileInputStream fis = new FileInputStream("jdbc.properties")) {
      Properties prop = new Properties();
      prop.load(fis);

      String strDbType = prop.getProperty("dao.factoryType");
    
      switch (strDbType) {
      case "h2":
        return new H2DAOFactory();
        default:
  	return null;
      }
    } catch (Exception ex) {
      LOG.error(ex);
      throw ex;
    }
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
