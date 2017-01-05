package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.File;
import java.util.List;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.nixsolutions.jdbc.bean.PersonType;
import com.nixsolutions.jdbc.dao.PersonTypeDAO;

public class H2PersonTypeDAOImplTest extends BaseTest {

  public H2PersonTypeDAOImplTest(String name) throws Exception {
    super(name);

    dao = factory.getPersonTypeDAO();
    
    insertDataset = "src/test/resources/PersonType_insert.xml";
    updateDataset = "src/test/resources/PersonType_update.xml";
    deleteDataset = "src/test/resources/PersonType_delete.xml";
    tableName = "person_type";
    
    insertBean = new PersonType("one more", "who knows");
    updateBean = new PersonType(2, "Teaching and grading", "upd");
    deleteId = 3;
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return new FlatXmlDataSetBuilder().build(new File(initialDataset));
  }  

  @Test
  public void testShouldGetAllPersonType() throws Exception {
    // when
    List<PersonType> grList = ((PersonTypeDAO) dao).getAll();

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
  public void testShouldGetPersonTypeById() throws Exception {
    // when
    PersonType ps = ((PersonTypeDAO) dao).getById(3);

    // then
    Assert.assertEquals("Mismatch in description", "Studying", ps.getDescription());
    Assert.assertEquals("Mismatch in value", "Student", ps.getValue());
  }
}
