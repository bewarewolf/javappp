package com.nixsolutions.jdbc.dao.impl.h2;

import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.GradeDAO;
import com.nixsolutions.jdbc.dao.JournalDAO;
import com.nixsolutions.jdbc.dao.PersonDAO;
import com.nixsolutions.jdbc.dao.PersonStatusDAO;
import com.nixsolutions.jdbc.dao.PersonTypeDAO;
import com.nixsolutions.jdbc.dao.PhoneNumberDAO;
import com.nixsolutions.jdbc.dao.RoleDAO;
import com.nixsolutions.jdbc.dao.SemesterDAO;
import com.nixsolutions.jdbc.dao.SubjectDAO;
import com.nixsolutions.jdbc.dao.UserDAO;

public class H2DAOFactory extends DAOFactory {

  @Override
  public GradeDAO getGradeDAO() {
    return new H2GradeDAOImpl();
  }

  @Override
  public JournalDAO getJournalDAO() {
    return new H2JournalDAOImpl();
  }

  @Override
  public PersonDAO getPersonDAO() {
    return new H2PersonDAOImpl();
  }

  @Override
  public PersonStatusDAO getPersonStatusDAO() {
    return new H2PersonStatusDAOImpl();
  }

  @Override
  public PersonTypeDAO getPersonTypeDAO() {
    return new H2PersonTypeDAOImpl();
  }

  @Override
  public PhoneNumberDAO getPhoneNumberDAO() {
    return new H2PhoneNumberDAOImpl();
  }

  @Override
  public SemesterDAO getSemesterDAO() {
    return new H2SemesterDAOImpl();
  }

  @Override
  public SubjectDAO getSubjectDAO() {
    return new H2SubjectDAOImpl();
  }

  @Override
  public UserDAO getUserDAO() {
    // TODO Auto-generated method stub
    return new H2UserDAOImpl();
  }

  @Override
  public RoleDAO getRoleDAO() {
    // TODO Auto-generated method stub
    return new H2RoleDAOImpl();
  }
}
