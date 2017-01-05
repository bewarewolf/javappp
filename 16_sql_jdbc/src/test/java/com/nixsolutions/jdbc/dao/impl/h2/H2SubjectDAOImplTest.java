package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.File;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.bean.Semester;
import com.nixsolutions.jdbc.bean.Subject;

public class H2SubjectDAOImplTest extends BaseTest {

  public H2SubjectDAOImplTest(String name) throws Exception {
    super(name);

    dao = factory.getSubjectDAO();
    
    insertDataset = "src/test/resources/Subject_insert.xml";
    updateDataset = "src/test/resources/Subject_update.xml";
    deleteDataset = "src/test/resources/Subject_delete.xml";
    tableName = "subject";
    
    Person p = new Person();
    p.setId(2);
    
    Semester s = new Semester();
    s.setId(3);
    
    insertBean = new Subject("new subject", p, s);
    
    updateBean = new Subject(2, "Reflection upd", p, s);
    deleteId = 3;
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return new FlatXmlDataSetBuilder().build(new File(initialDataset));
  }  

}
