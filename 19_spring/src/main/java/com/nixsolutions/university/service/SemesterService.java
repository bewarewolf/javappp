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
  
  @Transactional
  public void delete(Integer id) {
    semesterDao.delete(id);
  }
}
