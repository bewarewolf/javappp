package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.File;
import java.util.List;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.nixsolutions.jdbc.bean.PersonStatus;
import com.nixsolutions.jdbc.dao.PersonStatusDAO;

public class H2PersonStatusDAOImplTest extends BaseTest {

  public H2PersonStatusDAOImplTest(String name) throws Exception {
    super(name);

    dao = factory.getPersonStatusDAO();
    
    insertDataset = "src/test/resources/PersonStatus_insert.xml";
    updateDataset = "src/test/resources/PersonStatus_update.xml";
    deleteDataset = "src/test/resources/PersonStatus_delete.xml";
    tableName = "person_status";
    
    insertBean = new PersonStatus("new status", "some status");
    updateBean = new PersonStatus(3, "Activity is updated", "upd");
    deleteId = 4;
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return new FlatXmlDataSetBuilder().build(new File(initialDataset));
  }  

  @Test
  public void testShouldGetAllPersonStatus() throws Exception {
    // when
    List<PersonStatus> grList = ((PersonStatusDAO) dao).getAll();

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
  public void testShouldGetPersonStatusById() throws Exception {
    // when
    PersonStatus ps = ((PersonStatusDAO) dao).getById(4);

    // then
    Assert.assertEquals("Mismatch in description", ps.getDescription(), "Activity is terminated");
    Assert.assertEquals("Mismatch in value", ps.getValue(), "Expelled");
  }
}
