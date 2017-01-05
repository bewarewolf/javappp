package com.nixsolutions.jdbc.dao;

import java.util.List;

import com.nixsolutions.jdbc.bean.AbstractBean;

public interface GenericDAO<T extends AbstractBean> {

  int create(T bean);
  boolean delete(Integer id);
  boolean update(T bean);
  T getById(Integer id);
  List<T> getAll();
}
