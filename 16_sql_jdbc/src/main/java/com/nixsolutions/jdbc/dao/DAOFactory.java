package com.nixsolutions.jdbc.dao;

import java.sql.SQLException;

import com.nixsolutions.jdbc.dao.impl.h2.H2DAOFactory;

public abstract class DAOFactory {

  public static final int H2 = 0;
  
  public static DAOFactory getFactory(Integer type) {
    switch (type) {
    case H2:
      return new H2DAOFactory();
      default:
	throw new RuntimeException("Unsupported factory type");
    }
  }
  
  public abstract GradeDAO getGradeDAO() throws SQLException;
  
  public abstract JournalDAO getJournalDAO() throws SQLException;
  
  public abstract PersonDAO getPersonDAO() throws SQLException;
  
  public abstract PersonStatusDAO getPersonStatusDAO() throws SQLException;
  
  public abstract PersonTypeDAO getPersonTypeDAO() throws SQLException;
  
  public abstract PhoneNumberDAO getPhoneNumberDAO() throws SQLException;
  
  public abstract SemesterDAO getSemesterDAO() throws SQLException;
  
  public abstract SubjectDAO getSubjectDAO() throws SQLException;
}
