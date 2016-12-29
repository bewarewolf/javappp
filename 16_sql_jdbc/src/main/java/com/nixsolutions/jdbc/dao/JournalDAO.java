package com.nixsolutions.jdbc.dao;

import java.util.List;

import com.nixsolutions.jdbc.bean.Journal;
import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.bean.Subject;

public interface JournalDAO extends GenericDAO<Journal> {

  List<Journal> getBySubject(Subject subject);
  List<Journal> getBySubjectId(Integer subjectId);
  List<Journal> getByPerson(Person person);
  List<Journal> getByPersonId(Integer personId);
}
