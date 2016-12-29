package com.nixsolutions.jdbc.dao.impl.h2;

import java.sql.SQLException;

import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.GradeDAO;
import com.nixsolutions.jdbc.dao.JournalDAO;
import com.nixsolutions.jdbc.dao.PersonDAO;
import com.nixsolutions.jdbc.dao.PersonStatusDAO;
import com.nixsolutions.jdbc.dao.PersonTypeDAO;
import com.nixsolutions.jdbc.dao.PhoneNumberDAO;
import com.nixsolutions.jdbc.dao.SemesterDAO;
import com.nixsolutions.jdbc.dao.SubjectDAO;

public class H2DAOFactory extends DAOFactory {

  @Override
  public GradeDAO getGradeDAO() throws SQLException {
    return new H2GradeDAOImpl();
  }

  @Override
  public JournalDAO getJournalDAO() throws SQLException {
    return new H2JournalDAOImpl();
  }

  @Override
  public PersonDAO getPersonDAO() throws SQLException {
    return new H2PersonDAOImpl();
  }

  @Override
  public PersonStatusDAO getPersonStatusDAO() throws SQLException {
    return new H2PersonStatusDAOImpl();
  }

  @Override
  public PersonTypeDAO getPersonTypeDAO() throws SQLException {
    return new H2PersonTypeDAOImpl();
  }

  @Override
  public PhoneNumberDAO getPhoneNumberDAO() throws SQLException {
    return new H2PhoneNumberDAOImpl();
  }

  @Override
  public SemesterDAO getSemesterDAO() throws SQLException {
    return new H2SemesterDAOImpl();
  }

  @Override
  public SubjectDAO getSubjectDAO() throws SQLException {
    return new H2SubjectDAOImpl();
  }

}
