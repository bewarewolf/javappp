package com.nixsolutions.jdbc.dao.impl.h2;

import java.util.List;

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
import com.nixsolutions.jdbc.bean.PersonType;
import com.nixsolutions.jdbc.dao.PersonTypeDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:dbunit/DataSet.xml")
public class H2PersonTypeDAOImplTest {

  @Autowired
  private PersonTypeDAO personTypeDao;
  
  @Test
  @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
    table = "person_type",
    value = "classpath:dbunit/PersonType_insert.xml")
  public void testShouldCreatePersonType() throws Exception {
    // given
    PersonType pt = new PersonType("one more", "who knows");
    
    // when
    personTypeDao.create(pt);
  }
  
  @Test
  @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
    table = "person_type",
    value = "classpath:dbunit/PersonType_update.xml")
  public void testShouldUpdatePersonType() throws Exception {
    // given
    PersonType pt = personTypeDao.getById(2);
    pt.setValue("upd");
    
    // when
    personTypeDao.update(pt);    
  }

  @Test
  @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
    table = "person_type",
    value = "classpath:dbunit/PersonType_delete.xml")
  public void testShouldDeletePersonType() throws Exception {
    // when
    personTypeDao.delete(4);
  }
  
  @Test
  @DatabaseSetup("classpath:dbunit/DataSet.xml")
  public void testShouldGetAllPersonType() throws Exception {
    // when
    List<PersonType> grList = personTypeDao.getAll();

    
    Assert.assertEquals("Mismatch in value", 4, grList.size());
    
  }

  @Test
  public void testShouldGetPersonTypeById() throws Exception {
    // when
    PersonType ps = personTypeDao.getById(3);

    // then
    Assert.assertEquals("Mismatch in description", "Studying", ps.getDescription());
    Assert.assertEquals("Mismatch in value", "Student", ps.getValue());
  }
}
