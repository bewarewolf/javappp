package com.nixsolutions.jdbc.dao;

import java.sql.SQLException;

import com.nixsolutions.jdbc.bean.Semester;

public interface SemesterDAO extends GenericDAO<Semester> {

  Semester getByName(String name) throws SQLException;
  Semester currentSemester() throws SQLException;
}
