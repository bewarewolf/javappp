package com.nixsolutions.jdbc.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nixsolutions.jdbc.bean.PersonType;
import com.nixsolutions.jdbc.dao.PersonTypeDAO;
import com.nixsolutions.jdbc.util.HibernateUtil;

public class PersonTypeDAOImpl 
extends CrudDAO<PersonType> 
implements PersonTypeDAO {

  private static final Logger LOG = LogManager.getLogger();

  @Override
  public int create(PersonType bean) {
    return super.create(bean);
  }
  
  @Override
  public void update(PersonType bean) {
    super.update(bean);
  }
  
  @Override
  public void delete(Integer id) {
    PersonType bean = new PersonType();
    bean.setId(id);
    super.delete(bean);
  }

  @Override
  public PersonType getById(Integer id) {
    return super.getById(PersonType.class, id);
  }

  @Override
  public List<PersonType> getAll() {
    return super.getAll(PersonType.class);
  }

  @Override
  public PersonType getByValue(String value) {
    SessionFactory fact = null;
    Session ses = null;
    
    try {
      fact = HibernateUtil.getSessionFactory();
      ses = fact.openSession();
      Query query = ses.createQuery("from PersonType as pt where pt.value = :val");
      query.setParameter("val", value);
      return (PersonType) query.uniqueResult();
    } catch (Exception ex) {
      LOG.error(ex);
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  }    
}
