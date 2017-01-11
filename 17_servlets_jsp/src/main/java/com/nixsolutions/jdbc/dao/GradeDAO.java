package com.nixsolutions.jdbc.dao;

import java.sql.SQLException;

import com.nixsolutions.jdbc.bean.Grade;

public interface GradeDAO extends GenericDAO<Grade> {

  Grade getByDescription(String description) throws SQLException;
}
