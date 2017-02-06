package com.nixsolutions.jdbc.dao;

import com.nixsolutions.jdbc.bean.Semester;

public interface SemesterDAO extends GenericDAO<Semester> {

  Semester getByName(String name);
  Semester currentSemester();
}
