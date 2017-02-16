package com.nixsolutions.university.service;

import java.util.ArrayList;
import java.util.List;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.university.dao.JournalDAO;
import com.nixsolutions.university.dao.PersonDAO;
import com.nixsolutions.university.dao.PersonTypeDAO;
import com.nixsolutions.university.dao.SubjectDAO;
import com.nixsolutions.university.dto.JournalDTO;

@Service("journalService")
public class JournalService {

  @Autowired
  JournalDAO journalDao;
  
  @Autowired
  PersonDAO personDao;
  
  @Autowired
  PersonTypeDAO personTypeDao;
  
  @Autowired
  SubjectDAO subjectDao;
  
  public List<JournalDTO> getAll() {
    return JournalDTO.convertFromJournal(journalDao.getAll());
  }
  
  public List<JournalDTO> getAll(String subject, String student) {
    List<JournalDTO> out = getAll();
    
    if (!StringUtils.isNullOrEmpty(subject) && !"ALL".equals(subject)) {
      out.removeIf(j -> !j.getSubject().equals(subject));
    }
    if (!StringUtils.isNullOrEmpty(student) && !"ALL".equals(student)) {
      out.removeIf(j -> !j.getStudent().equals(student));
    }
    
    return out;
  }
  
  public List<String> getStudents() {
    List<String> out = new ArrayList<>();
    personDao.getByType(personTypeDao.getByValue("Student").getId()).forEach(t -> {
      out.add(t.getFirstName() + " " + t.getLastName());
    });
    return out;
  }
  
  public List<String> getSubjects() {
    List<String> out = new ArrayList<>();
    subjectDao.getAll().forEach(s -> out.add(s.getSubjectName()));    
    return out;
  }
  
  @Transactional
  public void delete(Integer id) {
    journalDao.delete(id);
  }
}
