package com.nixsolutions.jdbc.dao.impl;

import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.GradeDAO;
import com.nixsolutions.jdbc.dao.JournalDAO;
import com.nixsolutions.jdbc.dao.PersonDAO;
import com.nixsolutions.jdbc.dao.PersonStatusDAO;
import com.nixsolutions.jdbc.dao.PersonTypeDAO;
import com.nixsolutions.jdbc.dao.PhoneNumberDAO;
import com.nixsolutions.jdbc.dao.SemesterDAO;
import com.nixsolutions.jdbc.dao.SubjectDAO;

public class DAOFactoryImpl extends DAOFactory {

  @Override
  public GradeDAO getGradeDAO() {
    return new GradeDAOImpl();
  }

  @Override
  public JournalDAO getJournalDAO() {
    return new JournalDAOImpl();
  }

  @Override
  public PersonDAO getPersonDAO() {
    return new PersonDAOImpl();
  }

  @Override
  public PersonStatusDAO getPersonStatusDAO() {
    return new PersonStatusDAOImpl();
  }

  @Override
  public PersonTypeDAO getPersonTypeDAO() {
    return new PersonTypeDAOImpl();
  }

  @Override
  public PhoneNumberDAO getPhoneNumberDAO() {
    return new PhoneNumberDAOImpl();
  }

  @Override
  public SemesterDAO getSemesterDAO() {
    return new SemesterDAOImpl();
  }

  @Override
  public SubjectDAO getSubjectDAO() {
    return new SubjectDAOImpl();
  }
}
