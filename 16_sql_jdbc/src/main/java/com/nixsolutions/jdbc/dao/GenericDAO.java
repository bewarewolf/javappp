package com.nixsolutions.jdbc.dao;

import java.util.List;

public interface GenericDAO<T> {

  int create(T bean);
  boolean delete(Integer id);
  T getById(Integer id);
  List<T> getAll();
}
