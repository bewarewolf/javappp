package com.nixsolutions.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

  boolean create(T bean) throws SQLException;
  boolean delete(Integer id) throws SQLException;
  T getById(Integer id) throws SQLException;
  List<T> getAll() throws SQLException;
}
