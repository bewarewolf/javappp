package com.nixsolutions.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.university.dao.PersonDAO;
import com.nixsolutions.university.dao.PersonStatusDAO;
import com.nixsolutions.university.dao.PersonTypeDAO;
import com.nixsolutions.university.dao.PhoneNumberDAO;
import com.nixsolutions.university.dto.PersonDTO;
import com.nixsolutions.university.model.Person;
import com.nixsolutions.university.model.PersonStatus;
import com.nixsolutions.university.model.PersonType;
import com.nixsolutions.university.model.PhoneNumber;

@Service("personService")
public class PersonService {

  @Autowired
  PersonDAO personDao;
  
  @Autowired
  PersonStatusDAO personStatusDao;
  
  @Autowired
  PersonTypeDAO personTypeDao;
  
  @Autowired
  PhoneNumberDAO phoneNumberDao;
  
  public List<PersonDTO> getAll() {
    return PersonDTO.convertFromPerson(personDao.getAll());
  }
  
  public Person getById(Integer id) {
    return personDao.getById(id);
  }
  
  public PersonDTO getByLogin(String login) {
    Person p = personDao.getByLogin(login);
    PersonDTO out = new PersonDTO();
    out.setFirstName(p.getFirstName());
    out.setLastName(p.getLastName());
    out.setPersonType(p.getPersonType().getValue());
    return out;
  }
  
  public List<PersonType> getPersonTypes() {
    return personTypeDao.getAll();
  }
  
  public List<PersonStatus> getPersonStatuses() {
    return personStatusDao.getAll();
  }
  
  @Transactional
  public void delete(Integer id) {
    personDao.delete(id);
  }
  
  @Transactional
  public void saveOrUpdate(PersonDTO entity) {
    if (entity.getId() == null) {
      save(entity);
    } else {
      update(entity);
    }
  }  
  
  protected void save(PersonDTO entity) {
    Person pers = new Person();
    pers.setId(entity.getId());
    pers.setLogin(entity.getLogin());
    pers.setPassword(entity.getPassword());
    pers.setFirstName(entity.getFirstName());
    pers.setMiddleName(entity.getMiddleName());
    pers.setLastName(entity.getLastName());
    pers.setBirthday(entity.getBirthday());
    pers.setStartDate(entity.getStartDate());
    pers.setPersonType(personTypeDao.getByValue(entity.getPersonType()));
    pers.setPersonStatus(personStatusDao.getByValue(entity.getPersonStatus()));
    pers.setPhoneNumbers(entity.getPhoneNumbers());
    entity.setId(personDao.create(pers));
  }  
  
  protected void update(PersonDTO entity) {
    Person pers = getById(entity.getId());
    pers.setId(entity.getId());
    pers.setLogin(entity.getLogin());
    pers.setPassword(entity.getPassword());
    pers.setFirstName(entity.getFirstName());
    pers.setMiddleName(entity.getMiddleName());
    pers.setLastName(entity.getLastName());
    pers.setBirthday(entity.getBirthday());
    pers.setStartDate(entity.getStartDate());
    pers.setPersonType(personTypeDao.getByValue(entity.getPersonType()));
    pers.setPersonStatus(personStatusDao.getByValue(entity.getPersonStatus()));
    personDao.update(pers);
  }
  
  @Transactional
  public void deletePhoneNumber(Integer phoneId) {
    phoneNumberDao.delete(phoneId);
  }
  
  @Transactional
  public void addPhoneNumber(Integer personId, PhoneNumber phone) {
    phoneNumberDao.create(phone);
    Person pers = getById(personId);
    pers.getPhoneNumbers().add(phone);
    personDao.update(pers);
  }
}
