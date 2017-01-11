package com.nixsolutions.jdbc.dao;

import java.sql.SQLException;

import com.nixsolutions.jdbc.bean.PersonStatus;

public interface PersonStatusDAO extends GenericDAO<PersonStatus> {

  PersonStatus getByValue(String value) throws SQLException;
}
