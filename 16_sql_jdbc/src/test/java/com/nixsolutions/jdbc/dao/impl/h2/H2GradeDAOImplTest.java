package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.File;
import java.util.List;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;
import org.junit.Assert;

import com.nixsolutions.jdbc.bean.Grade;
import com.nixsolutions.jdbc.dao.GradeDAO;

public class H2GradeDAOImplTest extends BaseTest {

  public H2GradeDAOImplTest(String name) throws Exception {
    super(name);
    
    dao = factory.getGradeDAO();
    
    insertDataset = "src/test/resources/Grade_insert.xml";
    updateDataset = "src/test/resources/Grade_update.xml";
    deleteDataset = "src/test/resources/Grade_delete.xml";
    tableName = "grade";
    
    insertBean = new Grade("new grade", 15);
    updateBean = new Grade(5, "upd", 6);
    deleteId = 3;
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return new FlatXmlDataSetBuilder().build(
	new File(initialDataset));
  }

  @Test
  public void testShouldGetAllGrades() throws Exception {
    // when
    List<Grade> grList = ((GradeDAO) dao).getAll();

    // then
    IDataSet dbDataSet = getConnection().createDataSet();
    ITable actualTable = dbDataSet.getTable(tableName);

    Assert.assertEquals("Mismatch in list sizes", grList.size(), actualTable.getRowCount());

    for (int i = 0; i < grList.size(); i++) {
      Assert.assertEquals("Mismatch in description", grList.get(i).getDescription(),
	  actualTable.getValue(i, "description"));
      Assert.assertEquals("Mismatch in value", grList.get(i).getValue(), actualTable.getValue(i, "value"));
    }
  }

  @Test
  public void testShouldGradeById() throws Exception {
    // when
    Grade gr = ((GradeDAO) dao).getById(1);

    // then
    Assert.assertEquals("Mismatch in description", gr.getDescription(), "Fail");
    Assert.assertEquals("Mismatch in value", gr.getValue(), (Integer) 1);
  }
}
