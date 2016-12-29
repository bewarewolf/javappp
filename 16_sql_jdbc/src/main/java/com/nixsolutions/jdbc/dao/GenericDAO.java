package com.nixsolutions.jdbc.dao;

import java.util.List;

public interface GenericDAO<T> {

  boolean create(T bean);
  boolean delete(Integer id);
  T getById(Integer id);
  List<T> getAll();
}
