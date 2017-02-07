package com.nixsolutions.jdbc.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nixsolutions.jdbc.bean.Grade;
import com.nixsolutions.jdbc.dao.GradeDAO;
import com.nixsolutions.jdbc.util.HibernateUtil;

@Repository("gradeDao")
public class GradeDAOImpl 
extends CrudDAO<Grade> 
implements GradeDAO {

  private static final Logger LOG = LogManager.getLogger();
  
  @Override
  public int create(Grade bean) {
    return super.create(bean);
  }

  @Override
  public void update(Grade bean) {
    super.update(bean);
  }
  
  @Override
  public void delete(Integer id) {
    Grade bean = new Grade();
    bean.setId(id);
    super.delete(bean);
  }
    
  @Override
  public Grade getById(Integer id) {
    return super.getById(Grade.class, id);
  }

  @Override
  public List<Grade> getAll() {
    return super.getAll(Grade.class);
  }

  @Override
  public Grade getByDescription(String description) {
    Session ses = null;
    
    try {
      ses = sessionFactory.openSession();
      Query query = ses.createQuery("from Grade as g where g.description = :descr");
      query.setParameter("descr", description);
      return (Grade) query.uniqueResult();
    } catch (Exception ex) {
      LOG.error(ex);
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  }
}
