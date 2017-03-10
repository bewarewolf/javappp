package com.nixsolutions.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.university.dao.PersonTypeDAO;
import com.nixsolutions.university.model.PersonType;

@Service("personTypeService")
public class PersonTypeService {

  @Autowired
  PersonTypeDAO personTypeDao;
  
  public List<PersonType> getAll() {
    return personTypeDao.getAll();
  }
  
  public PersonType getByValue(String value) {
    return personTypeDao.getByValue(value);
  }
}
