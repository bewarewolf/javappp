package com.nixsolutions.university.dao;

import com.nixsolutions.university.model.Semester;

public interface SemesterDAO extends GenericDAO<Semester> {

  Semester getByName(String name);
  Semester currentSemester();
}
