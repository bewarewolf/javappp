package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.nixsolutions.jdbc.bean.PhoneNumber;
import com.nixsolutions.jdbc.dao.PhoneNumberDAO;

public class H2PhoneNumberDAOImplTest extends BaseTest {

  public H2PhoneNumberDAOImplTest(String name) throws Exception {
    super(name);

    dao = factory.getPhoneNumberDAO();
    
    insertDataset = "src/test/resources/PhoneNumber_insert.xml";
    updateDataset = "src/test/resources/PhoneNumber_update.xml";
    deleteDataset = "src/test/resources/PhoneNumber_delete.xml";
    tableName = "phone_number";
    
    insertBean = new PhoneNumber(1, "+380123456789");
    updateBean = new PhoneNumber(2, 2, "12-34-56");
    deleteId = 3;
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return new FlatXmlDataSetBuilder().build(new File(initialDataset));
  }  
  
  @Test
  public void testShouldGetPhoneNumberById() throws Exception {
    // given
    Integer pnId = 1;
    
    ITable expectedTable = getConnection().createQueryTable("RECORDS_BY_PERSON",
	String.format("SELECT * FROM phone_number WHERE phone_number_id = %d", pnId));

    // when
    PhoneNumber pn = ((PhoneNumberDAO) dao).getById(pnId);

    // then
    Assert.assertEquals("Mismatch in person id", pn.getPersonId(), expectedTable.getValue(0, "person_id"));
    Assert.assertEquals("Mismatch in phone number", pn.getPhoneNumber(), expectedTable.getValue(0, "phone_number"));
  }
  
  @Test
  public void testShouldGetPhoneNumberByPersonId() throws Exception {
 // given
    Integer persId = 1;
    
    ITable expectedTable = getConnection().createQueryTable("RECORDS_BY_PERSON",
	String.format("SELECT * FROM phone_number WHERE phone_number_id = %d", persId));

    // when
    List<PhoneNumber> pnList = ((PhoneNumberDAO) dao).getByPersonId(persId);

    // then
    Assert.assertEquals(expectedTable.getRowCount(), pnList.size());
  }
  
  @Test
  public void testShouldDeletePhoneNumberByPersonId() throws SQLException, Exception {
    // given
    Integer persId = 2;
    
    // when
    ((PhoneNumberDAO) dao).deleteByPersonId(persId);

    // then
    IDataSet dbDataSet = getConnection().createDataSet();
    ITable actualTable = dbDataSet.getTable(tableName);

    IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
	new File("src/test/resources/PhoneNumber_delete_by_person.xml"));
    ITable expectedTable = expectedDataSet.getTable(tableName);

    ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable,
	expectedTable.getTableMetaData().getColumns());

    Assertion.assertEquals(expectedTable, filteredTable);
  }
}
