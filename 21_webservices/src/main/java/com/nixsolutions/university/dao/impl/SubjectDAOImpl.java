package com.nixsolutions.university.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.university.dao.SubjectDAO;
import com.nixsolutions.university.model.Subject;

@Repository("subjectDao")
@Transactional
public class SubjectDAOImpl extends CrudDAO<Subject> implements SubjectDAO {

  @Override
  public int create(Subject bean) {
    return super.create(bean);
  }

  @Override
  public void update(Subject bean) {
    super.update(bean);
  }

  @Override
  public void delete(Integer id) {
    Subject bean = new Subject();
    bean.setId(id);
    super.delete(bean);
  }

  @Override
  public Subject getById(Integer id) {
    return super.getById(Subject.class, id);
  }

  @Override
  public List<Subject> getAll() {
    return super.getAll(Subject.class);
  }

  @Override
  public Subject getByName(String name) {
    Session ses = sessionFactory.getCurrentSession();
    Query query = ses.createQuery("from Subject as s where s.subjectName = :name");
    query.setParameter("name", name);
    Subject out = (Subject) query.uniqueResult();
    return out;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Subject> getByTeacherId(Integer teacherId) {
    Session ses = sessionFactory.getCurrentSession();
    Query query = ses.createQuery("from Subject as s where s.teacher.id = :id");
    query.setParameter("id", teacherId);
    List<Subject> out = (List<Subject>) query.list();
    return out;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Subject> getBySemesterId(Integer semesterId) {
    Session ses = sessionFactory.getCurrentSession();
    Query query = ses.createQuery("from Subject as s where s.semester.id = :id");
    query.setParameter("id", semesterId);
    List<Subject> out = (List<Subject>) query.list();
    return out;
  }
}
