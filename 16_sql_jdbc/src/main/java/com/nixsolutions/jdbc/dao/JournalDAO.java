package com.nixsolutions.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.jdbc.bean.Journal;

public interface JournalDAO extends GenericDAO<Journal> {

  List<Journal> getBySubjectId(Integer subjectId) throws SQLException;
  List<Journal> getByPersonId(Integer personId) throws SQLException;
}
