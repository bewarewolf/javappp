package com.nixsolutions.university.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T extends Serializable> {

  int create(T bean);
  void delete(Integer id);
  void update(T bean);
  T getById(Integer id);
  List<T> getAll();
}
