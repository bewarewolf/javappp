package com.nixsolutions.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.jdbc.bean.Subject;

public interface SubjectDAO extends GenericDAO<Subject> {

  List<Subject> getByTeacherId(Integer teacherId) throws SQLException;
  List<Subject> getBySemesterId(Integer semesterId) throws SQLException;
}
