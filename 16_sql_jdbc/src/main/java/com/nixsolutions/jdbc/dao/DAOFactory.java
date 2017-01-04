package com.nixsolutions.jdbc.dao;

import com.nixsolutions.jdbc.dao.impl.h2.H2DAOFactory;

public abstract class DAOFactory {

  public static final int H2 = 0;
  
  public static DAOFactory getFactory(Integer type) {
    switch (type) {
    case H2:
      return new H2DAOFactory();
      default:
	return null;
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
