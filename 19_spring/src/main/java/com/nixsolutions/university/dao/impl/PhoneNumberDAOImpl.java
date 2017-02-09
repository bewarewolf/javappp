package com.nixsolutions.university.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.university.dao.PhoneNumberDAO;
import com.nixsolutions.university.model.PhoneNumber;

@Repository("phoneNumberDao")
public class PhoneNumberDAOImpl extends CrudDAO<PhoneNumber> implements PhoneNumberDAO {

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
    Session ses = sessionFactory.openSession();
    Query query = ses.createQuery("from PhoneNumber as pn where pn.value = :val");
    query.setParameter("val", number);
    PhoneNumber out = (PhoneNumber) query.uniqueResult();
    ses.close();
    return out;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<PhoneNumber> getByPersonId(Integer id) {
    Session ses = sessionFactory.openSession();
    Query query = ses.createQuery("from PhoneNumber as pn where pn.personId = :val");
    query.setParameter("val", id);
    List<PhoneNumber> out = (List<PhoneNumber>) query.list();
    ses.close();
    return out;
  }

  @Override
  @Transactional
  public void deleteByPersonId(Integer id) {
    Session ses = sessionFactory.openSession();
    Query query = ses.createQuery("delete from PhoneNumber as pn where pn.personId = :val");
    query.setParameter("val", id);
    query.executeUpdate();
    ses.close();    
  }
}
