package com.nixsolutions.university.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nixsolutions.university.dao.PersonDAO;
import com.nixsolutions.university.model.Person;

@Repository("personDao")
public class PersonDAOImpl extends CrudDAO<Person> implements PersonDAO {

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
    Session ses = sessionFactory.openSession();
    Query query = ses.createQuery("from Person as p where p.firstName = :fn and p.lastName = :ln");
    query.setParameter("fn", firstName);
    query.setParameter("ln", lastName);
    Person out = (Person) query.uniqueResult();
    ses.close();
    return out;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Person> getByType(Integer typeId) {
    Session ses = sessionFactory.openSession();
    Query query = ses.createQuery("from Person as p where p.personType.id = :id");
    query.setParameter("id", typeId);
    List<Person> out = (List<Person>) query.list();
    ses.close();
    return out;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Person> getByStatus(Integer statusId) {
    Session ses = sessionFactory.openSession();
    Query query = ses.createQuery("from Person as p where p.personStatus.id = :id");
    query.setParameter("id", statusId);
    List<Person> out = (List<Person>) query.list();
    ses.close();
    return out;
  }
}
