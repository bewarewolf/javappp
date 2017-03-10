package com.nixsolutions.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.university.dao.SemesterDAO;
import com.nixsolutions.university.model.Semester;

@Service("semesterService")
public class SemesterService {

  @Autowired
  SemesterDAO semesterDao;
  
  public List<Semester> getAll() {
    return semesterDao.getAll();
  }
  
  public Semester getById(Integer id) {
    return semesterDao.getById(id);
  }
  
  @Transactional
  public void delete(Integer id) {
    semesterDao.delete(id);
  }
  
  @Transactional
  public Integer saveOrUpdate(Semester in) {
    if (in.getId() != null) {
      Semester sem = semesterDao.getById(in.getId());
      sem.setSemesterName(in.getSemesterName());
      sem.setStartDate(in.getStartDate());
      sem.setEndDate(in.getEndDate());
      semesterDao.update(sem);
    } else {
      semesterDao.create(in);
    }
    
    return in.getId();
  }
  
  @Transactional
  public Integer saveOrUpdate(com.nixsolutions.university.ws.soap.semester.Semester in) {
    Semester s = new Semester();
    if (in.getId() != null) {
      s.setId(Integer.valueOf(in.getId()));
    }
    s.setSemesterName(in.getSemesterName());
    s.setStartDate(in.getStartDate().toGregorianCalendar().toZonedDateTime().toLocalDate());
    s.setEndDate(in.getEndDate().toGregorianCalendar().toZonedDateTime().toLocalDate());
    return saveOrUpdate(s);
  }
}
