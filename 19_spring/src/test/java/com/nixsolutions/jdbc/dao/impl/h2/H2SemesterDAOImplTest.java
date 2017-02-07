package com.nixsolutions.jdbc.dao.impl.h2;

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
import com.nixsolutions.jdbc.bean.Semester;
import com.nixsolutions.jdbc.dao.SemesterDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:dbunit/DataSet.xml")
public class H2SemesterDAOImplTest {
/*
  public H2SemesterDAOImplTest(String name) throws Exception {
    super(name);

    dao = factory.getSemesterDAO();
    
    insertDataset = "src/test/resources/Semester_insert.xml";
    updateDataset = "src/test/resources/Semester_update.xml";
    deleteDataset = "src/test/resources/Semester_delete.xml";
    tableName = "semester";
    
    insertBean = new Semester("new semester", LocalDate.parse("2017-06-01"), LocalDate.parse("2017-12-31"));
    updateBean = new Semester(2, "2nd upd", LocalDate.parse("2016-01-06"), LocalDate.parse("2016-05-03"));
    deleteId = 3;
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return new FlatXmlDataSetBuilder().build(new File(initialDataset));
  }  */

  @Autowired
  private SemesterDAO semesterDao;
  
  @Test
  @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
    table = "semester",
    value = "classpath:dbunit/Semester_insert.xml")
  public void testShouldCreateSemester() throws Exception {
    // given
    Semester s = new Semester("new semester", LocalDate.parse("2017-06-01"), LocalDate.parse("2017-12-31"));
    
    // when
    semesterDao.create(s);
  }
  
  @Test
  @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
    table = "semester",
    value = "classpath:dbunit/Semester_update.xml")
  public void testShouldUpdateSemester() throws Exception {
    // given
    Semester s = semesterDao.getById(2);
    s.setSemesterName("2nd upd");
    s.setStartDate(LocalDate.parse("2016-01-06"));
    s.setEndDate(LocalDate.parse("2016-05-03"));
    
    // when
    semesterDao.update(s);    
  }

  @Test
  @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
    table = "semester",
    value = "classpath:dbunit/Semester_delete.xml")
  public void testShouldDeleteSemester() throws Exception {
    // when
    semesterDao.delete(3);
  }
  
  @Test
  public void testShouldGetSemesterById() throws Exception {
    // given
    
    // when
    Semester sem = semesterDao.getById(1);

    // then
    Assert.assertEquals("Mismatch in semester name", sem.getSemesterName(), "1st");
    Assert.assertEquals("Mismatch in start date", 
	sem.getStartDate(), LocalDate.parse("2015-09-01"));
    Assert.assertEquals("Mismatch in end date", 
	sem.getEndDate(),LocalDate.parse("2015-12-31"));
  }
  
  @Test
  public void testShouldGetCurrentSemester() throws Exception {    
    // when
    Semester sem = semesterDao.currentSemester();

    // then
    Assert.assertTrue("Start date is after current", LocalDate.now().isAfter(sem.getStartDate()));
    Assert.assertTrue("End date is before current", LocalDate.now().isBefore(sem.getEndDate()));
  }
}
