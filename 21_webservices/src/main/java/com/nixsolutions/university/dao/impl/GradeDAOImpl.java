package com.nixsolutions.university.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.university.dao.GradeDAO;
import com.nixsolutions.university.model.Grade;

@Repository("gradeDao")
@Transactional
public class GradeDAOImpl extends CrudDAO<Grade> implements GradeDAO {

  @Override
  public int create(Grade bean) {
    return super.create(bean);
  }

  @Override
  public void update(Grade bean) {
    super.update(bean);
  }

  @Override
  public void delete(Integer id) {
    Grade bean = new Grade();
    bean.setId(id);
    super.delete(bean);
  }

  @Override
  public Grade getById(Integer id) {
    return super.getById(Grade.class, id);
  }

  @Override
  public List<Grade> getAll() {
    return super.getAll(Grade.class);
  }

  @Override
  public Grade getByDescription(String description) {
    Session ses = sessionFactory.getCurrentSession();
    Query query = ses.createQuery("from Grade as g where g.description = :descr");
    query.setParameter("descr", description);
    Grade out = (Grade) query.uniqueResult();
    return out;
  }
}
