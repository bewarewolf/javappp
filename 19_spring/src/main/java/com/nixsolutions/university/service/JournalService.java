package com.nixsolutions.university.service;

import java.util.List;
import java.util.stream.Collectors;

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
    return JournalDTO.convertFromJournal(journalDao.getAll(subject, student));
  }
  
  public List<String> getStudents() {
    return personDao.getByType(personTypeDao.getByValue("Student").getId())
	    .stream().map(p -> p.getFirstName() + " " + p.getLastName()).collect(Collectors.toList());
  }
  
  public List<String> getSubjects() {
    return subjectDao.getAll()
	    .stream().map(s -> s.getSubjectName()).collect(Collectors.toList());
  }
  
  @Transactional
  public void delete(Integer id) {
    journalDao.delete(id);
  }
}
