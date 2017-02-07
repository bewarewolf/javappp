package com.nixsolutions.jdbc.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nixsolutions.jdbc.bean.PersonStatus;
import com.nixsolutions.jdbc.dao.PersonStatusDAO;
import com.nixsolutions.jdbc.util.HibernateUtil;

@Repository("personStatusDao")
public class PersonStatusDAOImpl 
extends CrudDAO<PersonStatus> 
implements PersonStatusDAO {

  private static final Logger LOG = LogManager.getLogger();
  
  @Override
  public int create(PersonStatus bean) {
    return super.create(bean);
  }
  
  @Override
  public void update(PersonStatus bean) {
    super.update(bean);
  }
  
  @Override
  public void delete(Integer id) {
    PersonStatus bean = new PersonStatus();
    bean.setId(id);
    super.delete(bean);
  }

  @Override
  public PersonStatus getById(Integer id) {
    return super.getById(PersonStatus.class, id);
  }

  @Override
  public List<PersonStatus> getAll() {
    return super.getAll(PersonStatus.class);
  }

  @Override
  public PersonStatus getByValue(String value) {
    Session ses = null;
    
    try {
      ses = sessionFactory.openSession();
      Query query = ses.createQuery("from PersonStatus as ps where ps.value = :val");
      query.setParameter("val", value);
      return (PersonStatus) query.uniqueResult();
    } catch (Exception ex) {
      LOG.error(ex);
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  }  
}
