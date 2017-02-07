package com.nixsolutions.jdbc.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.dao.PersonDAO;
import com.nixsolutions.jdbc.util.HibernateUtil;

@Repository("personDao")
public class PersonDAOImpl 
extends CrudDAO<Person> 
implements PersonDAO {

  private static final Logger LOG = LogManager.getLogger();
  
  @Override
  public int create(Person bean) {
    return super.create(bean);
  }

  @Override
  public void delete(Integer id) {
    Person bean = new Person();
    bean.setId(id);
    super.delete(bean);
  }

  @Override
  public void update(Person bean) {
    super.update(bean);
  }

  @Override
  public Person getById(Integer id) {
    return super.getById(Person.class, id);
  }

  @Override
  public List<Person> getAll() {
    return super.getAll(Person.class);
  }

  @Override
  public Person getByName(String firstName, String lastName) {
    Session ses = null;
    
    try {
      ses = sessionFactory.openSession();
      Query query = ses.createQuery("from Person as p where p.firstName = :fn and p.lastName = :ln");
      query.setParameter("fn", firstName);
      query.setParameter("ln", lastName);
      return (Person) query.uniqueResult();
    } catch (Exception ex) {
      LOG.error(ex);
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Person> getByType(Integer typeId) {
    Session ses = null;
    
    try {
      ses = sessionFactory.openSession();
      Query query = ses.createQuery("from Person as p where p.personType.id = :id");
      query.setParameter("id", typeId);
      return (List<Person>) query.list();
    } catch (Exception ex) {
      LOG.error(ex);
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Person> getByStatus(Integer statusId) {
    Session ses = null;
    
    try {
      ses = sessionFactory.openSession();
      Query query = ses.createQuery("from Person as p where p.personStatus.id = :id");
      query.setParameter("id", statusId);
      return (List<Person>) query.list();
    } catch (Exception ex) {
      LOG.error(ex);
      throw new RuntimeException(ex);
    } finally {
      HibernateUtil.closeQuietly(ses);
    }
  }

  
}
