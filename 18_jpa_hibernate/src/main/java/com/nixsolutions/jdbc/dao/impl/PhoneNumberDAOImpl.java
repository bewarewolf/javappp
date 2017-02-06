package com.nixsolutions.jdbc.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nixsolutions.jdbc.bean.PhoneNumber;
import com.nixsolutions.jdbc.dao.PhoneNumberDAO;
import com.nixsolutions.jdbc.util.HibernateUtil;

public class PhoneNumberDAOImpl 
extends CrudDAO<PhoneNumber> 
implements PhoneNumberDAO {

  private static final Logger LOG = LogManager.getLogger();

  @Override
  public int create(PhoneNumber bean) {
    return super.create(bean);
  }
  
  @Override
  public void update(PhoneNumber bean) {
    super.update(bean);
  }
  
  @Override
  public void delete(Integer id) {
    PhoneNumber bean = new PhoneNumber();
    bean.setId(id);
    super.delete(bean);
  }

  @Override
  public PhoneNumber getById(Integer id) {
    return super.getById(PhoneNumber.class, id);
  }

  @Override
  public List<PhoneNumber> getAll() {
    return super.getAll(PhoneNumber.class);
  }

  @Override
  public PhoneNumber getByNumber(String number) {
    SessionFactory fact = null;
    Session ses = null;
    
    try {
      fact = HibernateUtil.getSessionFactory();
      ses = fact.openSession();
      Query query = ses.createQuery("from PhoneNumber as pn where pn.value = :val");
      query.setParameter("val", number);
      return (PhoneNumber) query.uniqueResult();
    } catch (Exception ex) {
      LOG.error(ex);
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<PhoneNumber> getByPersonId(Integer id) {
    SessionFactory fact = null;
    Session ses = null;
    
    try {
      fact = HibernateUtil.getSessionFactory();
      ses = fact.openSession();
      Query query = ses.createQuery("from PhoneNumber as pn where pn.personId = :val");
      query.setParameter("val", id);
      return (List<PhoneNumber>) query.list();
    } catch (Exception ex) {
      LOG.error(ex);
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  }

  @Override
  public void deleteByPersonId(Integer id) {
    // TODO Auto-generated method stub
  }
}
