package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.File;
import java.time.LocalDate;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import com.nixsolutions.jdbc.bean.Grade;
import com.nixsolutions.jdbc.bean.Journal;
import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.bean.Subject;

public class H2JournalDAOImplTest extends BaseTest {

  public H2JournalDAOImplTest(String name) throws Exception {
    super(name);

    dao = factory.getJournalDAO();
    
    insertDataset = "src/test/resources/Journal_insert.xml";
    updateDataset = "src/test/resources/Journal_update.xml";
    deleteDataset = "src/test/resources/Journal_delete.xml";
    tableName = "journal";
    
    Person p = new Person();
    p.setId(2);
    
    Subject s = new Subject();
    s.setId(2);
    
    Grade g = new Grade();
    g.setId(4);
    
    insertBean = new Journal(p, s, g, LocalDate.now());
    updateBean = new Journal(3, p, s, g, LocalDate.now());
    deleteId = 3;
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return new FlatXmlDataSetBuilder().build(new File(initialDataset));
  }  

}
