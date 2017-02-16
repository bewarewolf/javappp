package com.nixsolutions.university.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.university.dao.JournalDAO;
import com.nixsolutions.university.model.Journal;

@Repository("journalDao")
@Transactional
public class JournalDAOImpl extends CrudDAO<Journal> implements JournalDAO {

  @Override
  public int create(Journal bean) {
    return super.create(bean);
  }

  @Override
  public void delete(Integer id) {
    Journal bean = new Journal();
    bean.setId(id);
    super.delete(bean);
  }

  @Override
  public void update(Journal bean) {
    super.update(bean);
  }

  @Override
  public Journal getById(Integer id) {
    return super.getById(Journal.class, id);
  }

  @Override
  public List<Journal> getAll() {
    return super.getAll(Journal.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Journal> getBySubjectId(Integer subjectId) {
    Session ses = sessionFactory.getCurrentSession();
    Query query = ses.createQuery("from Journal as g where g.subject.id = :id");
    query.setParameter("id", subjectId);
    List<Journal> out = (List<Journal>) query.list();
    return out;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Journal> getByPersonId(Integer personId) {
    Session ses = sessionFactory.getCurrentSession();
    Query query = ses.createQuery("from Journal as g where g.person.id = :id");
    query.setParameter("id", personId);
    List<Journal> out = (List<Journal>) query.list();
    return out;
  }
}
