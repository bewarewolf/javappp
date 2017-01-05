package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.File;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import com.nixsolutions.jdbc.bean.PhoneNumber;

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
}
