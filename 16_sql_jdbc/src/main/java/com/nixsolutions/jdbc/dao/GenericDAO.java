package com.nixsolutions.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.jdbc.bean.AbstractBean;

public interface GenericDAO<T extends AbstractBean> {

  int create(T bean) throws SQLException;
  boolean delete(Integer id) throws SQLException;
  boolean update(T bean) throws SQLException;
  T getById(Integer id) throws SQLException;
  List<T> getAll() throws SQLException;
}
