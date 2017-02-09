package com.nixsolutions.university.dao.impl;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.nixsolutions.university.dao.PersonDAO;
import com.nixsolutions.university.dao.PersonStatusDAO;
import com.nixsolutions.university.dao.PersonTypeDAO;
import com.nixsolutions.university.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:dbunit/DataSet.xml")
public class H2PersonDAOImplTest {

  @Autowired
  private PersonDAO personDao;
  
  @Autowired
  private PersonTypeDAO personTypeDao;
  
  @Autowired
  private PersonStatusDAO personStatusDao;
    
  @Test
  @DatabaseSetup("classpath:dbunit/DataSet.xml")
  @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
    table = "person",
    value = "classpath:dbunit/Person_insert.xml")
  public void testShouldCreatePerson() throws Exception {
    // given
    Person p = new Person("Jarod", null, "Fry", 
	LocalDate.parse("1993-03-15"), 
	LocalDate.parse("2015-08-15"), 
	personTypeDao.getById(2), 
	personStatusDao.getById(1), 
	"jarod", 
	"jarod");
    
    // when
    personDao.create(p);
  }
  
  @Test
  @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
    table = "person",
    value = "classpath:dbunit/Person_update.xml")
  public void testShouldUpdatePerson() throws Exception {
    // given
    Person p = personDao.getById(3);
    p.setFirstName("Mattew");
    p.setMiddleName(null);
    p.setLastName("upd");
    // when
    personDao.update(p);    
  }

  @Test
  @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
    table = "person",
    value = "classpath:dbunit/Person_delete.xml")
  public void testShouldDeletePerson() throws Exception {
    // when
    personDao.delete(3);
  }
  
  @Test
  public void testShouldGetPersonById() throws Exception {    
    // when
    Person pers = personDao.getById(1);

    // then
    Assert.assertEquals("Mismatch in first name", "Averill", pers.getFirstName());
    Assert.assertEquals("Mismatch in middle name", "Humphrey", pers.getMiddleName());
    Assert.assertEquals("Mismatch in last name", "Samuelson", pers.getLastName());
    Assert.assertEquals("Mismatch in birthday", LocalDate.parse("1916-09-25"), pers.getBirthday());
    Assert.assertEquals("Mismatch in start date", LocalDate.parse("2006-06-25"), pers.getStartDate());
    Assert.assertEquals("Mismatch in person_status_id", new Integer(2), pers.getPersonStatus().getId());
    Assert.assertEquals("Mismatch in person_type_id", new Integer(1), pers.getPersonType().getId());
    Assert.assertEquals("Mismatch in login", "root", pers.getLogin());
    Assert.assertEquals("Mismatch in first name", "root", pers.getPassword());
  }
  
  @Test
  public void testShouldGetPersonByName() throws Exception {   
    // when
    Person pers = personDao.getByName("Averill", "Samuelson");

    // then
    Assert.assertEquals("Mismatch in first name", "Averill", pers.getFirstName());
    Assert.assertEquals("Mismatch in middle name", "Humphrey", pers.getMiddleName());
    Assert.assertEquals("Mismatch in last name", "Samuelson", pers.getLastName());
    Assert.assertEquals("Mismatch in birthday", LocalDate.parse("1916-09-25"), pers.getBirthday());
    Assert.assertEquals("Mismatch in start date", LocalDate.parse("2006-06-25"), pers.getStartDate());
    Assert.assertEquals("Mismatch in person_status_id", personStatusDao.getById(2).getId(), pers.getPersonStatus().getId());
    Assert.assertEquals("Mismatch in person_type_id", personTypeDao.getById(1).getId(), pers.getPersonType().getId());
    Assert.assertEquals("Mismatch in login", "root", pers.getLogin());
    Assert.assertEquals("Mismatch in first name", "root", pers.getPassword());
  }
}
