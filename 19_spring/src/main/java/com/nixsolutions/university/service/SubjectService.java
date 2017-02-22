package com.nixsolutions.university.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.university.dao.PersonDAO;
import com.nixsolutions.university.dao.PersonTypeDAO;
import com.nixsolutions.university.dao.SemesterDAO;
import com.nixsolutions.university.dao.SubjectDAO;
import com.nixsolutions.university.dto.SubjectDTO;
import com.nixsolutions.university.model.Subject;

@Service("subjectService")
public class SubjectService {
  
  @Autowired
  SubjectDAO subjectDao;
  
  @Autowired
  SemesterDAO semesterDao;
  
  @Autowired
  PersonDAO personDao;
  
  @Autowired
  PersonTypeDAO personTypeDao;
  
  public List<SubjectDTO> getAll() {
    return SubjectDTO.convertFromSubject(subjectDao.getAll());
  }
  
  public SubjectDTO getyId(Integer id) {
    return new SubjectDTO(subjectDao.getById(id));
  }
  
  @Transactional
  public void delete(Integer id) {
    subjectDao.delete(id);
  }
  
  @Transactional
  public void saveOrUpdate(SubjectDTO dto) {
    Subject subject;
    if (dto.getId() == null) {
      subject = new Subject();
    } else {
      subject = subjectDao.getById(dto.getId());
    }
    
    subject.setId(dto.getId());
    subject.setSubjectName(dto.getName());
    subject.setSemester(semesterDao.getByName(dto.getSemester()));
    String[] name = dto.getTeacher().split(" ");
    subject.setTeacher(personDao.getByName(name[0], name[1]));
    
    if (subject.getId() == null) {
      dto.setId(subjectDao.create(subject));
    } else {
      subjectDao.update(subject);
    }
  }
  
  public List<String> getSemesters() {
    return semesterDao.getAll().stream().map(s -> s.getSemesterName()).collect(Collectors.toList());
  }
  
  public List<String> getTeachers() {
    return personDao.getByType(personTypeDao.getByValue("Teacher").getId())
	.stream().map(p -> p.getFullName()).collect(Collectors.toList());
  }
}
