package com.nixsolutions.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.jdbc.bean.Person;

public interface PersonDAO extends GenericDAO<Person> {

  Person getByName(String firstName, String lastName) throws SQLException;
  List<Person> getByType(Integer typeId) throws SQLException;
  List<Person> getByStatus(Integer statusId) throws SQLException;
}
