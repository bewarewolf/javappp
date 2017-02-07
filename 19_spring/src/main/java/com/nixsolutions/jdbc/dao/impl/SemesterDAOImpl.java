package com.nixsolutions.jdbc.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nixsolutions.jdbc.bean.Semester;
import com.nixsolutions.jdbc.dao.SemesterDAO;
import com.nixsolutions.jdbc.util.HibernateUtil;

@Repository("semesterDao")
public class SemesterDAOImpl extends CrudDAO<Semester> 
implements SemesterDAO {

  private static final Logger LOG = LogManager.getLogger();
  
  @Override
  public int create(Semester bean) {
    return super.create(bean);
  }
  
  @Override
  public void delete(Semester bean) {
    super.delete(bean);
  }
  
  @Override
  public void delete(Integer id) {
    Semester bean = new Semester();
    bean.setId(id);
    super.delete(bean);
  }

  @Override
  public Semester getById(Integer id) {
    return super.getById(Semester.class, id);
  }

  @Override
  public List<Semester> getAll() {
    return super.getAll(Semester.class);
  }

  @Override
  public Semester getByName(String name) {
    Session ses = null;
    
    try {
      ses = sessionFactory.openSession();
      Query query = ses.createQuery("from Semester as s where s.semesterName = :name");
      query.setParameter("name", name);
      return (Semester) query.uniqueResult();
    } catch (Exception ex) {
      LOG.error(ex);
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  }

  @Override
  public Semester currentSemester() {
    Session ses = null;
    
    try {
      ses = sessionFactory.openSession();
      Query query = ses.createQuery("from Semester as s where current_date() "
          + "between s.startDate and s.endDate");
      return (Semester) query.uniqueResult();
    } catch (Exception ex) {
      LOG.error(ex);
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  }  
}
