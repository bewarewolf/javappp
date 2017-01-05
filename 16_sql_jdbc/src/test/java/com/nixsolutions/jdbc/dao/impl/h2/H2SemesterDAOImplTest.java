package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.File;
import java.time.LocalDate;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import com.nixsolutions.jdbc.bean.Semester;

public class H2SemesterDAOImplTest extends BaseTest {

  public H2SemesterDAOImplTest(String name) throws Exception {
    super(name);

    dao = factory.getSemesterDAO();
    
    insertDataset = "src/test/resources/Semester_insert.xml";
    updateDataset = "src/test/resources/Semester_update.xml";
    deleteDataset = "src/test/resources/Semester_delete.xml";
    tableName = "semester";
    
    insertBean = new Semester("new semester", LocalDate.parse("2017-01-01"), LocalDate.parse("2017-05-31"));
    updateBean = new Semester(2, "2nd upd", LocalDate.parse("2016-01-06"), LocalDate.parse("2016-05-03"));
    deleteId = 3;
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return new FlatXmlDataSetBuilder().build(new File(initialDataset));
  }  

}
