package com.nixsolutions.university.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nixsolutions.university.dao.SemesterDAO;
import com.nixsolutions.university.model.Semester;

@Repository("semesterDao")
public class SemesterDAOImpl extends CrudDAO<Semester> implements SemesterDAO {

  @Override
  public int create(Semester bean) {
    return super.create(bean);
  }

  @Override
  public void delete(Semester bean) {
    super.delete(bean);
  }

  @Override
  public void delete(Integer id) {
    Semester bean = new Semester();
    bean.setId(id);
    super.delete(bean);
  }

  @Override
  public Semester getById(Integer id) {
    return super.getById(Semester.class, id);
  }

  @Override
  public List<Semester> getAll() {
    return super.getAll(Semester.class);
  }

  @Override
  public Semester getByName(String name) {
    Session ses = sessionFactory.openSession();
    Query query = ses.createQuery("from Semester as s where s.semesterName = :name");
    query.setParameter("name", name);
    Semester out = (Semester) query.uniqueResult();
    ses.close();
    return out;
  }

  @Override
  public Semester currentSemester() {
    Session ses = sessionFactory.openSession();
    Query query = ses.createQuery("from Semester as s where current_date() " + "between s.startDate and s.endDate");
    Semester out = (Semester) query.uniqueResult();
    ses.close();
    return out;
  }
}
