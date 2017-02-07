package com.nixsolutions.jdbc.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nixsolutions.jdbc.bean.Journal;
import com.nixsolutions.jdbc.dao.JournalDAO;
import com.nixsolutions.jdbc.util.HibernateUtil;

@Repository("journalDao")
public class JournalDAOImpl 
extends CrudDAO<Journal> 
implements JournalDAO {

  private static final Logger LOG = LogManager.getLogger();
  
  @Override
  public int create(Journal bean) {
    return super.create(bean);
  }

  @Override
  public void delete(Integer id) {
    Journal bean = new Journal();
    bean.setId(id);
    super.delete(bean);
  }

  @Override
  public void update(Journal bean) {
    super.update(bean);
  }

  @Override
  public Journal getById(Integer id) {
    return super.getById(Journal.class, id);
  }

  @Override
  public List<Journal> getAll() {
    return super.getAll(Journal.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Journal> getBySubjectId(Integer subjectId) {
    Session ses = null;
    
    try {
      ses = sessionFactory.openSession();
      Query query = ses.createQuery("from Journal as g where g.subject.id = :id");
      query.setParameter("id", subjectId);
      return (List<Journal>) query.list();
    } catch (Exception ex) {
      LOG.error(ex);
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Journal> getByPersonId(Integer personId) {
    Session ses = null;
    
    try {
      ses = sessionFactory.openSession();
      Query query = ses.createQuery("from Journal as g where g.person.id = :id");
      query.setParameter("id", personId);
      return (List<Journal>) query.list();
    } catch (Exception ex) {
      LOG.error(ex);
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  }  
}
