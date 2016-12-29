package com.nixsolutions.jdbc.dao;

import com.nixsolutions.jdbc.dao.impl.h2.H2DAOFactory;

public abstract class DAOFactory {

  public static final Integer H2 = 0;
  
  public static DAOFactory getFactory(Integer type) {
    switch (type) {
    case 0:
      return new H2DAOFactory();
      default:
	throw new RuntimeException("Unsupported factory type");
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
