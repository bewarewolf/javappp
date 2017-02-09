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
public abstract class CrudDAO<T extends Serializable> {
  
  @Autowired
  protected SessionFactory sessionFactory;

  @Transactional
  public int create(T bean) {
    Session ses = sessionFactory.openSession();
    Integer id = (Integer) ses.save(bean);
    ses.flush();
    ses.close();
    return id;
  }

  @Transactional
  public void delete(T bean) {
    Session ses = sessionFactory.openSession();
    ses.delete(bean);
    ses.flush();
    ses.close();
  }

  @Transactional
  public void update(T bean) {
    Session ses = sessionFactory.openSession();
    ses.merge(bean);
    ses.flush();
    ses.close();
  }

  @SuppressWarnings("unchecked")
  public T getById(Class<T> type, Integer id) {
    Session ses = sessionFactory.openSession();
    T out = (T) ses.get(type, id);
    ses.close();
    return out;
  }

  @SuppressWarnings("unchecked")
  public List<T> getAll(Class<T> type) {
    Session ses = sessionFactory.openSession();
    Criteria crit = ses.createCriteria(type);
    List<T> out = (List<T>) crit.list();
    ses.close();
    return out;
  }
}
