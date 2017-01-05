package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.File;
import java.util.List;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.nixsolutions.jdbc.bean.Subject;
import com.nixsolutions.jdbc.dao.SubjectDAO;

public class H2SubjectDAOImplTest extends BaseTest {

  public H2SubjectDAOImplTest(String name) throws Exception {
    super(name);

    dao = factory.getSubjectDAO();

    insertDataset = "src/test/resources/Subject_insert.xml";
    updateDataset = "src/test/resources/Subject_update.xml";
    deleteDataset = "src/test/resources/Subject_delete.xml";
    tableName = "subject";

    insertBean = new Subject("new subject", 2, 3);
    updateBean = new Subject(2, "Reflection upd", 2, 3);
    deleteId = 3;
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return new FlatXmlDataSetBuilder().build(new File(initialDataset));
  }

  @Test
  public void testShouldGetSubjectById() throws Exception {
    // given
    Integer subId = 1;

    ITable expectedTable = getConnection().createQueryTable("SUBJECT",
	String.format("SELECT * FROM subject WHERE subject_id = %d", subId));

    // when
    Subject sub = ((SubjectDAO) dao).getById(subId);

    // then
    Assert.assertEquals("Mismatch in person id", sub.getSubjectName(), expectedTable.getValue(0, "subject_name"));
    Assert.assertEquals("Mismatch in phone number", sub.getTeacherId(), expectedTable.getValue(0, "teacher_id"));
    Assert.assertEquals("Mismatch in phone number", sub.getSemesterId(), expectedTable.getValue(0, "semester_id"));
  }

  @Test
  public void testShouldGetSubjectByTeacherId() throws Exception {
    // given
    Integer persId = 2;

    ITable expectedTable = getConnection().createQueryTable("SUBJECT_BY_TEACHER",
	String.format("SELECT * FROM subject WHERE teacher_id = %d", persId));

    // when
    List<Subject> subList = ((SubjectDAO) dao).getByTeacherId(persId);

    // then
    Assert.assertEquals(expectedTable.getRowCount(), subList.size());
  }

  @Test
  public void testShouldGetSubjectBySemesterId() throws Exception {
    // given
    Integer semId = 1;

    ITable expectedTable = getConnection().createQueryTable("SUBJECT_BY_TEACHER",
	String.format("SELECT * FROM subject WHERE semester_id = %d", semId));

    // when
    List<Subject> subList = ((SubjectDAO) dao).getBySemesterId(semId);

    // then
    Assert.assertEquals(expectedTable.getRowCount(), subList.size());
  }
}
