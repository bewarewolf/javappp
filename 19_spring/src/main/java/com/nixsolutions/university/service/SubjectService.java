package com.nixsolutions.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.university.dao.SubjectDAO;
import com.nixsolutions.university.dto.SubjectDTO;

@Service("subjectService")
public class SubjectService {
  
  @Autowired
  SubjectDAO subjectDao;
  
  public List<SubjectDTO> getAll() {
    return SubjectDTO.convertFromSubject(subjectDao.getAll());
  }
  
  @Transactional
  public void delete(Integer id) {
    subjectDao.delete(id);
  }
}
