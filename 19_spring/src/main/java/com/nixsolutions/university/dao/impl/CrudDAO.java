package com.nixsolutions.university.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public abstract class CrudDAO<T extends Serializable> {
  
  @Autowired
  protected SessionFactory sessionFactory;

  public int create(T bean) {
    Session ses = sessionFactory.getCurrentSession();
    Integer id = (Integer) ses.save(bean);
    return id;
  }

  public void delete(T bean) {
    Session ses = sessionFactory.getCurrentSession();
    ses.delete(bean);
  }

  public void update(T bean) {
    Session ses = sessionFactory.getCurrentSession();
    ses.merge(bean);
  }

  @SuppressWarnings("unchecked")
  public T getById(Class<T> type, Integer id) {
    Session ses = sessionFactory.getCurrentSession();
    T out = (T) ses.get(type, id);
    return out;
  }

  @SuppressWarnings("unchecked")
  public List<T> getAll(Class<T> type) {
    Session ses = sessionFactory.getCurrentSession();
    Criteria crit = ses.createCriteria(type);
    List<T> out = (List<T>) crit.list();
    return out;
  }
}
