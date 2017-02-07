package com.nixsolutions.jdbc.dao.impl.h2;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.junit.Assert;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.nixsolutions.jdbc.bean.Grade;
import com.nixsolutions.jdbc.dao.GradeDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:dbunit/DataSet.xml")
public class H2GradeDAOImplTest {

  @Autowired
  private GradeDAO gradeDao;
    
  @Test
  @DatabaseSetup("classpath:dbunit/DataSet.xml")
  @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
    table = "grade",
    value = "classpath:dbunit/Grade_insert.xml")
  public void testShouldCreateGrade() throws Exception {
    // given
    Grade gr = new Grade("new grade", 15);
    
    // when
    gradeDao.create(gr);
  }
  
  @Test
  @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
    table = "grade",
    value = "classpath:dbunit/Grade_update.xml")
  public void testShouldUpdateGrade() throws Exception {
    // given
    Grade gr = gradeDao.getById(5);
    gr.setDescription("upd");
    gr.setValue(6);
    // when
    gradeDao.update(gr);    
  }

  @Test
  @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
    table = "grade",
    value = "classpath:dbunit/Grade_delete.xml")
  public void testShouldDeleteGrade() throws Exception {
    // when
    gradeDao.delete(3);
  }
    
  @Test
  public void testShouldGetAllGrades() throws Exception {
    // when
    List<Grade> grList = gradeDao.getAll();

    // then    
    Assert.assertEquals("Mismatch in list sizes", grList.size(), 5);
  }

  @Test
  public void testShouldGetGradeById() throws Exception {
    // when
    Grade gr = gradeDao.getById(1);

    // then
    Assert.assertEquals("Mismatch in description", gr.getDescription(), "Fail");
    Assert.assertEquals("Mismatch in value", gr.getValue(), (Integer) 1);
  }
}
