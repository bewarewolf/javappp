package com.nixsolutions.jdbc.dao;

import java.sql.SQLException;

import com.nixsolutions.jdbc.bean.PersonType;

public interface PersonTypeDAO extends GenericDAO<PersonType> {

  PersonType getByValue(String value) throws SQLException;
}
