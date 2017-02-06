package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.bean.PersonStatus;
import com.nixsolutions.jdbc.bean.PersonType;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.PersonDAO;

public class H2PersonDAOImplTest extends BaseTest {

  public H2PersonDAOImplTest(String name) throws Exception {
    super(name);

    dao = factory.getPersonDAO();

    insertDataset = "src/test/resources/Person_insert.xml";
    updateDataset = "src/test/resources/Person_update.xml";
    deleteDataset = "src/test/resources/Person_delete.xml";
    tableName = "person";

    PersonStatus ps = DAOFactory.getFactory().getPersonStatusDAO().getById(1);
    PersonType pt = DAOFactory.getFactory().getPersonTypeDAO().getById(2);
    
    insertBean = new Person("Jarod", null, "Fry", 
	LocalDate.parse("1993-03-15"), LocalDate.parse("2015-08-15"), 
	pt, ps, 
	"jarod", "jarod");
    
    Person upd = DAOFactory.getFactory().getPersonDAO().getById(3);
    upd.setFirstName("Mattew");
    upd.setMiddleName(null);
    upd.setLastName("upd");
    updateBean = upd;
    
	//new Person(3, "Mattew", null, "upd", 
	//LocalDate.parse("1963-02-13"), LocalDate.parse("2012-08-15"), 
	//pt, ps, 
	//"matt", "matt");
    
    deleteId = 3;
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return new FlatXmlDataSetBuilder().build(new File(initialDataset));
  }

  @Test
  public void testShouldGetPersonById() throws Exception {
    // given
    Integer persId = 1;
    
    ITable expectedTable = getConnection().createQueryTable("RECORDS_BY_PERSON",
	String.format("SELECT * FROM person WHERE person_id = %d", persId));

    // when
    Person pers = ((PersonDAO) dao).getById(persId);

    // then
    Assert.assertEquals("Mismatch in first name", pers.getFirstName(), expectedTable.getValue(0, "first_name"));
    Assert.assertEquals("Mismatch in middle name", pers.getMiddleName(), expectedTable.getValue(0, "middle_name"));
    Assert.assertEquals("Mismatch in last name", pers.getLastName(), expectedTable.getValue(0, "last_name"));
    Assert.assertEquals("Mismatch in birthday", 
	pers.getBirthday(), ((java.sql.Date)expectedTable.getValue(0, "birthday")).toLocalDate());
    Assert.assertEquals("Mismatch in start date", 
	pers.getStartDate(), ((java.sql.Date)expectedTable.getValue(0, "date_start")).toLocalDate());
    Assert.assertEquals("Mismatch in person_status_id", 
	pers.getPersonStatus().getId(), expectedTable.getValue(0, "person_status_id"));
    Assert.assertEquals("Mismatch in person_type_id", 
	pers.getPersonType().getId(), expectedTable.getValue(0, "person_type_id"));
  }
  
  @Test
  public void testShouldGetPersonByName() throws Exception {
    // given
    String fName = "Averill";
    String lName = "Samuelson";
    
    ITable expectedTable = getConnection().createQueryTable("RECORDS_BY_PERSON",
	String.format("SELECT * FROM person WHERE first_name = '%s' AND last_name = '%s'", fName, lName));

    // when
    Person pers = ((PersonDAO) dao).getByName(fName, lName);

    // then
    Assert.assertEquals("Mismatch in first name", pers.getFirstName(), expectedTable.getValue(0, "first_name"));
    Assert.assertEquals("Mismatch in middle name", pers.getMiddleName(), expectedTable.getValue(0, "middle_name"));
    Assert.assertEquals("Mismatch in last name", pers.getLastName(), expectedTable.getValue(0, "last_name"));
    Assert.assertEquals("Mismatch in birthday", 
	pers.getBirthday(), ((java.sql.Date)expectedTable.getValue(0, "birthday")).toLocalDate());
    Assert.assertEquals("Mismatch in start date", 
	pers.getStartDate(), ((java.sql.Date)expectedTable.getValue(0, "date_start")).toLocalDate());
    Assert.assertEquals("Mismatch in person_status_id", 
	pers.getPersonStatus().getId(), expectedTable.getValue(0, "person_status_id"));
    Assert.assertEquals("Mismatch in person_type_id", 
	pers.getPersonType().getId(), expectedTable.getValue(0, "person_type_id"));
  }
  
  @Test
  public void testShouldGetPersonByPersonType() throws Exception {
    // given
    Integer ptId = 2;
    
    ITable expectedTable = getConnection().createQueryTable("RECORDS_BY_PERSON",
	String.format("SELECT * FROM person WHERE person_type_id = %d", ptId));

    // when
    List<Person> pers = ((PersonDAO) dao).getByType(ptId);
    
    // then    
    Assert.assertEquals(expectedTable.getRowCount(), pers.size());
  }
  
  @Test
  public void testShouldGetPersonByPersonStatus() throws Exception {
    // given
    Integer psId = 1;
    
    ITable expectedTable = getConnection().createQueryTable("RECORDS_BY_PERSON",
	String.format("SELECT * FROM person WHERE person_status_id = %d", psId));

    // when
    List<Person> pers = ((PersonDAO) dao).getByStatus(psId);
    
    // then    
    Assert.assertEquals(expectedTable.getRowCount(), pers.size());
  }
}
