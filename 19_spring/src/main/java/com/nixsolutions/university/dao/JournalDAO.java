package com.nixsolutions.university.dao;

import java.util.List;

import com.nixsolutions.university.model.Journal;

public interface JournalDAO extends GenericDAO<Journal> {

  List<Journal> getBySubjectId(Integer subjectId);
  List<Journal> getByPersonId(Integer personId);
}
