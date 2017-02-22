package com.nixsolutions.university.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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
  public List<Journal> getAll(String subjectName, String personName) {
    Session ses = sessionFactory.getCurrentSession();
    Criteria cr = ses.createCriteria(Journal.class);
    
    if (subjectName != null) {
      cr.createAlias("subject", "s").add(Restrictions.eq("s.subjectName", subjectName));
    }
    
    if (personName != null) {
      cr.createAlias("person", "p").add(Restrictions.eq("p.fullName", personName));
    }
    
    return cr.list();
  }  
}
