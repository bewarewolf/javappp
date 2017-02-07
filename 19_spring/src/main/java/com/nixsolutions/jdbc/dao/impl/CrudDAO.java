package com.nixsolutions.jdbc.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.jdbc.util.HibernateUtil;

@Repository
public abstract class CrudDAO<T extends Serializable> {

  private static final Logger LOG = LogManager.getLogger();
  
  @Autowired
  protected SessionFactory sessionFactory;
  
  @Transactional
  public int create(T bean) {
    Session ses = null;
    Transaction tx = null;
    
    try {
      ses = sessionFactory.openSession();
      tx = ses.beginTransaction();      
      Integer id = (Integer) ses.save(bean);
      tx.commit();
      return id;
    } catch (Exception ex) {
      LOG.error(ex);
      tx.rollback();
      throw new RuntimeException(ex);
    } finally {           
      HibernateUtil.closeQuietly(ses);
    }
  }

  @Transactional
  public void delete(T bean) {
    Session ses = null;
    Transaction tx = null;
    
    try {
      ses = sessionFactory.openSession();
      tx = ses.beginTransaction();
      ses.delete(bean);
      tx.commit();
    } catch (Exception ex) {
      LOG.error(ex);
      tx.rollback();
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  }

  @Transactional
  public void update(T bean) {
    Session ses = null;
    Transaction tx = null;
    
    try {
      ses = sessionFactory.openSession();
      tx = ses.beginTransaction();
      ses.merge(bean);
      tx.commit();
    } catch (Exception ex) {
      LOG.error(ex);
      tx.rollback();
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  }

  @SuppressWarnings("unchecked")
  public T getById(Class<T> type, Integer id) {
    Session ses = null;
    
    try {  
      ses = sessionFactory.openSession();
      return (T) ses.get(type, id);
    } catch (Exception ex) {
      LOG.error(ex);
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  }

  @SuppressWarnings("unchecked")
  public List<T> getAll(Class<T> type) {
    Session ses = null;
    
    try {
      ses = sessionFactory.openSession();
      Criteria crit = ses.createCriteria(type);
      List<T> out = (List<T>) crit.list();
      return out;
    } catch (Exception ex) {
      LOG.error(ex);
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  }
}
