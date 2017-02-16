package com.nixsolutions.university.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.university.dao.PersonTypeDAO;
import com.nixsolutions.university.model.PersonType;

@Repository("personTypeDao")
@Transactional
public class PersonTypeDAOImpl extends CrudDAO<PersonType> implements PersonTypeDAO {

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
    Session ses = sessionFactory.getCurrentSession();
    Query query = ses.createQuery("from person_type as pt where pt.value = :val");
    query.setParameter("val", value);
    PersonType out = (PersonType) query.uniqueResult();
    return out;
  }
}
