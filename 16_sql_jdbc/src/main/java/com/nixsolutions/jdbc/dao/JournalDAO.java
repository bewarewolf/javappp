package com.nixsolutions.jdbc.dao;

import java.util.List;

import com.nixsolutions.jdbc.bean.Journal;

public interface JournalDAO extends GenericDAO<Journal> {

  List<Journal> getBySubjectId(Integer subjectId);
  List<Journal> getByPersonId(Integer personId);
}
