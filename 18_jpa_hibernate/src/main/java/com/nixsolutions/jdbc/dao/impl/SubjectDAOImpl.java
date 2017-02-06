package com.nixsolutions.jdbc.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nixsolutions.jdbc.bean.Subject;
import com.nixsolutions.jdbc.dao.SubjectDAO;
import com.nixsolutions.jdbc.util.HibernateUtil;

public class SubjectDAOImpl 
extends CrudDAO<Subject> 
implements SubjectDAO {

  private static final Logger LOG = LogManager.getLogger();

  @Override
  public int create(Subject bean) {
    return super.create(bean);
  }
  
  @Override
  public void update(Subject bean) {
    super.update(bean);
  }
  
  @Override
  public void delete(Integer id) {
    Subject bean = new Subject();
    bean.setId(id);
    super.delete(bean);
  }

  @Override
  public Subject getById(Integer id) {
    return super.getById(Subject.class, id);
  }

  @Override
  public List<Subject> getAll() {
    return super.getAll(Subject.class);
  }

  @Override
  public Subject getByName(String name) {
    SessionFactory fact = null;
    Session ses = null;
    
    try {
      fact = HibernateUtil.getSessionFactory();
      ses = fact.openSession();
      Query query = ses.createQuery("from Subject as s where s.subjectName = :name");
      query.setParameter("name", name);
      return (Subject) query.uniqueResult();
    } catch (Exception ex) {
      LOG.error(ex);
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Subject> getByTeacherId(Integer teacherId) {
    SessionFactory fact = null;
    Session ses = null;
    
    try {
      fact = HibernateUtil.getSessionFactory();
      ses = fact.openSession();
      Query query = ses.createQuery("from Subject as s where s.teacher.id = :id");
      query.setParameter("id", teacherId);
      return (List<Subject>) query.list();
    } catch (Exception ex) {
      LOG.error(ex);
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Subject> getBySemesterId(Integer semesterId) {
    SessionFactory fact = null;
    Session ses = null;
    
    try {
      fact = HibernateUtil.getSessionFactory();
      ses = fact.openSession();
      Query query = ses.createQuery("from Subject as s where s.semester.id = :id");
      query.setParameter("id", semesterId);
      return (List<Subject>) query.list();
    } catch (Exception ex) {
      LOG.error(ex);
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  } 
}
