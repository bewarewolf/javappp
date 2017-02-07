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
import com.nixsolutions.jdbc.bean.PersonStatus;
import com.nixsolutions.jdbc.dao.PersonStatusDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:dbunit/DataSet.xml")
public class H2PersonStatusDAOImplTest  {

  @Autowired
  private PersonStatusDAO personStatusDao;
  
  @Test
  @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
    table = "person_status",
    value = "classpath:dbunit/PersonStatus_insert.xml")
  public void testShouldCreatePersonStatus() throws Exception {
    // given
    PersonStatus ps = new PersonStatus("new status", "some status");
    
    // when
    personStatusDao.create(ps);
  }
  
  @Test
  @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
    table = "person_status",
    value = "classpath:dbunit/PersonStatus_update.xml")
  public void testShouldUpdatePersonStatus() throws Exception {
    // given
    PersonStatus ps = personStatusDao.getById(3);
    ps.setDescription("Activity is updated");
    ps.setValue("upd");
    
    // when
    personStatusDao.update(ps);    
  }

  @Test
  @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
    table = "person_status",
    value = "classpath:dbunit/PersonStatus_delete.xml")
  public void testShouldDeletePersonStatus() throws Exception {
    // when
    personStatusDao.delete(4);
  }
  
  @Test
  public void testShouldGetAllPersonStatus() throws Exception {
    // when
    List<PersonStatus> grList = personStatusDao.getAll();

    // then
    Assert.assertEquals("Mismatch in list sizes", grList.size(), 4);
  }

  @Test
  public void testShouldGetPersonStatusById() throws Exception {
    // when
    PersonStatus ps = personStatusDao.getById(4);

    // then
    Assert.assertEquals("Mismatch in description", ps.getDescription(), "Activity is terminated");
    Assert.assertEquals("Mismatch in value", ps.getValue(), "Expelled");
  }
}
