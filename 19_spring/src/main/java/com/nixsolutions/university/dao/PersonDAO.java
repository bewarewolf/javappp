package com.nixsolutions.university.dao;

import java.util.List;

import com.nixsolutions.university.model.Person;

public interface PersonDAO extends GenericDAO<Person> {

  Person getByName(String firstName, String lastName);
  List<Person> getByType(Integer typeId);
  List<Person> getByStatus(Integer statusId);
  Person getByLogin(String login);
}
