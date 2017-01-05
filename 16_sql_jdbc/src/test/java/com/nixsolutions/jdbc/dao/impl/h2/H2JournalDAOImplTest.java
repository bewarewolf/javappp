package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.nixsolutions.jdbc.bean.Grade;
import com.nixsolutions.jdbc.bean.Journal;
import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.bean.Subject;
import com.nixsolutions.jdbc.dao.JournalDAO;

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
    
    insertBean = new Journal(2, 2, 4, LocalDate.now());
    updateBean = new Journal(3, 2, 2, 4, LocalDate.now());
    deleteId = 3;
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return new FlatXmlDataSetBuilder().build(new File(initialDataset));
  }  

  @Test
  public void testShouldGetRecordById() throws Exception {
    // given
    Integer recId = 1;
    ITable actualTable = getConnection().createQueryTable("RECORDS_BY_SUBJECT",
        String.format("SELECT * FROM journal WHERE record_id = %d", recId));
    
    // when
    Journal rec = ((JournalDAO) dao).getById(1);

    // then    
    Assert.assertEquals("Mismatch in person id", rec.getPersonId(), actualTable.getValue(0, "person_id"));
    Assert.assertEquals("Mismatch in subject id", rec.getSubjectId(), actualTable.getValue(0, "subject_id"));
    Assert.assertEquals("Mismatch in grade id", rec.getGradeId(), actualTable.getValue(0, "grade_id"));
  }
  
  @Test
  public void testShouldGetAllRecords() throws Exception {
    // when
    List<Journal> grList = ((JournalDAO) dao).getAll();

    // then
    IDataSet dbDataSet = getConnection().createDataSet();
    ITable actualTable = dbDataSet.getTable(tableName);

    Assert.assertEquals("Mismatch in list sizes", grList.size(), actualTable.getRowCount());
  }
  
  @Test
  public void testShouldGetRecordsBySubjectId() throws Exception {
    // given
    Integer subjId = 1;
    
    // when
    List<Journal> grList = ((JournalDAO) dao).getBySubjectId(subjId);

    // then
    ITable actualTable = getConnection().createQueryTable("RECORDS_BY_SUBJECT",
        String.format("SELECT * FROM journal WHERE subject_id = %d", subjId));

    Assert.assertEquals("Mismatch in list sizes", grList.size(), actualTable.getRowCount());
  }
  
  @Test
  public void testShouldGetRecordsByPersonId() throws Exception {
    // given
    Integer persId = 2;
    
    // when
    List<Journal> grList = ((JournalDAO) dao).getByPersonId(persId);

    // then
    ITable actualTable = getConnection().createQueryTable("RECORDS_BY_PERSON",
        String.format("SELECT * FROM journal WHERE person_id = %d", persId));

    Assert.assertEquals("Mismatch in list sizes", grList.size(), actualTable.getRowCount());
  }
}
