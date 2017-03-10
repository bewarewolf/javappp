package com.nixsolutions.university.dao;

import java.util.List;

import com.nixsolutions.university.model.Journal;

public interface JournalDAO extends GenericDAO<Journal> {

  List<Journal> getAll(String subjectName, String personName);
}
