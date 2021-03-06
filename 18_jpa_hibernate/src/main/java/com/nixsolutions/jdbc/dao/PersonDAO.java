package com.nixsolutions.jdbc.dao;

import java.util.List;

import com.nixsolutions.jdbc.bean.Person;

public interface PersonDAO extends GenericDAO<Person> {

  Person getByName(String firstName, String lastName);
  List<Person> getByType(Integer typeId);
  List<Person> getByStatus(Integer statusId);
}
