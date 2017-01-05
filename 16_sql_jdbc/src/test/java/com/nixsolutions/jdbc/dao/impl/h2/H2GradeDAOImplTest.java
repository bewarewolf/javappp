package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.nixsolutions.jdbc.bean.Grade;
import com.nixsolutions.jdbc.dao.GradeDAO;

public class H2GradeDAOImplTest extends DBTestCase {

  private GradeDAO dao = new H2GradeDAOImpl();

  public H2GradeDAOImplTest(String name) throws Exception {
    super(name);
    FileInputStream fis = new FileInputStream("jdbc.properties");
    Properties prop = new Properties();
    prop.load(fis);

    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, prop.getProperty("db.driver.h2"));
    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, prop.getProperty("db.url.h2"));
    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, prop.getProperty("db.username.h2"));
    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, prop.getProperty("db.password.h2"));
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    // TODO Auto-generated method stub
    return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/Grade.xml"));
  }

  @Test
  public void testCreateNewGrade() throws Exception {
    // when
    dao.create(new Grade("new grade", 15));

    // Fetch database data after executing your code
    IDataSet databaseDataSet = getConnection().createDataSet();
    ITable actualTable = databaseDataSet.getTable("grade");

    // Load expected data from an XML dataset
    IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/resources/Grade_insert.xml"));
    ITable expectedTable = expectedDataSet.getTable("grade");

    ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable,
        expectedTable.getTableMetaData().getColumns());

    // Assert actual database table match expected table
    Assertion.assertEquals(expectedTable, filteredTable);
  }
}
