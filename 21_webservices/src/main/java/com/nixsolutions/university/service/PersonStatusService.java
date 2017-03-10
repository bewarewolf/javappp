package com.nixsolutions.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.university.dao.PersonStatusDAO;
import com.nixsolutions.university.model.PersonStatus;

@Service("personStatusService")
public class PersonStatusService {

  @Autowired
  PersonStatusDAO personStatusDao;
  
  public List<PersonStatus> getAll() {
    return personStatusDao.getAll();
  }
  
  public PersonStatus getByValue(String value) {
    return personStatusDao.getByValue(value);
  }
}
