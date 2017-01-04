package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.nixsolutions.jdbc.bean.Grade;
import com.nixsolutions.jdbc.dao.GradeDAO;

public class H2GradeDAOImplTest extends BaseTest {

  private GradeDAO dao = new H2GradeDAOImpl();
  
  public H2GradeDAOImplTest(String name) throws Exception {
    super(name);
  }
  
  protected IDataSet getDataSet() throws Exception
  {
      return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/GradeTestDataSet.xml"));
  }
  
  @Test
  public void testShouldAddNewGrade() throws Exception {
    //given
    Grade g1 = new Grade();
    g1.setDescription("new grade");
    g1.setValue(15);
    
    // when
    
    dao.create(g1);
    
    //then
    IDataSet databaseDataSet = getConnection().createDataSet();
    ITable actualTable = databaseDataSet.getTable("grade");

    IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
        new FileInputStream("src/test/resources/GradeTestDataSet_insert.xml"));

    ITable expectedTable = expectedDataSet.getTable("GRADE");
    ITable filteredActualTable = DefaultColumnFilter
        .includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());

    Assertion.assertEquals(expectedTable, filteredActualTable);
  }
}
