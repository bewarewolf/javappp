package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.File;
import java.time.LocalDate;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.bean.PersonStatus;
import com.nixsolutions.jdbc.bean.PersonType;

public class H2PersonDAOImplTest extends BaseTest {

  public H2PersonDAOImplTest(String name) throws Exception {
    super(name);

    dao = factory.getPersonDAO();
    
    insertDataset = "src/test/resources/Person_insert.xml";
    updateDataset = "src/test/resources/Person_update.xml";
    deleteDataset = "src/test/resources/Person_delete.xml";
    tableName = "person";
    
    PersonType pt = new PersonType();
    pt.setId(2);
    
    PersonStatus ps = new PersonStatus();
    ps.setId(1);
    
    insertBean = new Person("Jarod", null, "Fry", LocalDate.parse("1993-03-15"), 
	LocalDate.parse("2015-08-15"), pt, ps, null);
    updateBean = new Person(3, "Mattew", null, "upd", LocalDate.parse("1963-02-13"), 
	LocalDate.parse("2012-08-15"), pt, ps, null);
    deleteId = 3;
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return new FlatXmlDataSetBuilder().build(new File(initialDataset));
  }  

}
