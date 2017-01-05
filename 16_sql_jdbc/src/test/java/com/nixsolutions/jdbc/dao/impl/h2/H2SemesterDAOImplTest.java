package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.File;
import java.time.LocalDate;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.nixsolutions.jdbc.bean.Semester;
import com.nixsolutions.jdbc.dao.SemesterDAO;

public class H2SemesterDAOImplTest extends BaseTest {

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
  }  

  @Test
  public void testShouldGetSemesterById() throws Exception {
    // given
    Integer semId = 1;
    
    ITable expectedTable = getConnection().createQueryTable("RECORDS_BY_PERSON",
	String.format("SELECT * FROM semester WHERE semester_id = %d", semId));

    // when
    Semester sem = ((SemesterDAO) dao).getById(semId);

    // then
    Assert.assertEquals("Mismatch in semester name", sem.getSemesterName(), expectedTable.getValue(0, "semester_name"));
    Assert.assertEquals("Mismatch in start date", 
	sem.getStartDate(), ((java.sql.Date)expectedTable.getValue(0, "semester_date_start")).toLocalDate());
    Assert.assertEquals("Mismatch in end date", 
	sem.getEndDate(), ((java.sql.Date)expectedTable.getValue(0, "semester_date_end")).toLocalDate());
  }
  
  @Test
  public void testShouldGetCurrentSemester() throws Exception {    
    // when
    Semester sem = ((SemesterDAO) dao).currentSemester();

    // then
    Assert.assertTrue("Start date is after current", LocalDate.now().isAfter(sem.getStartDate()));
    Assert.assertTrue("End date is before current", LocalDate.now().isBefore(sem.getEndDate()));
  }
}
