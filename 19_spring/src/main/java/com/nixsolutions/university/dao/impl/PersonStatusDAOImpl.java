package com.nixsolutions.university.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nixsolutions.university.dao.PersonStatusDAO;
import com.nixsolutions.university.model.PersonStatus;

@Repository("personStatusDao")
public class PersonStatusDAOImpl extends CrudDAO<PersonStatus> implements PersonStatusDAO {

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
    Session ses = sessionFactory.openSession();
    Query query = ses.createQuery("from PersonStatus as ps where ps.value = :val");
    query.setParameter("val", value);
    PersonStatus out = (PersonStatus) query.uniqueResult();
    ses.close();
    return out;
  }
}
