package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.GenericDAO;
import com.nixsolutions.jdbc.dao.impl.DAOFactoryImpl;

public abstract class BaseTest extends DBTestCase {

  private static final Logger LOG = LogManager.getLogger();

  protected IDatabaseTester dbTest;
  
  protected String initialDataset = "src/test/resources/DataSet.xml";
  protected String insertDataset;
  protected String updateDataset;
  protected String deleteDataset;
  protected String tableName;
  
  protected DAOFactory factory;
  @SuppressWarnings("rawtypes")
  protected GenericDAO dao;
  
  protected Serializable insertBean;
  protected Serializable updateBean;
  protected Integer deleteId;
  
  public BaseTest(String name) throws Exception {
    super(name);
    
    factory = DAOFactoryImpl.getFactory();
    
    Properties prop = new Properties();

    try (InputStream fis = BaseTest.class.getClassLoader().getResourceAsStream("jdbc.properties");) {
      prop.load(fis);
    } catch (IOException ex) {
      LOG.error(ex);
    }  

    String urlPattern = prop.getProperty("db.url.h2");
    String schema = prop.getProperty("db.schema");
    String url = BaseTest.class.getClassLoader().getResource(schema + ".mv.db").toString();
       
    String urlFinal = String.format(urlPattern, url.substring(0, url.lastIndexOf(schema) + schema.length()));
    
    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, prop.getProperty("db.driver.h2"));
    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, urlFinal);
    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, prop.getProperty("db.username.h2"));
    System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, prop.getProperty("db.password.h2"));
  }
  
  @SuppressWarnings("unchecked")
  @Test
  public void testShouldCreateNewBean() throws Exception {
    // when
    dao.create(insertBean);

    // then
    IDataSet databaseDataSet = getConnection().createDataSet();
    ITable actualTable = databaseDataSet.getTable(tableName);

    IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
	new File(insertDataset));
    ITable expectedTable = expectedDataSet.getTable(tableName);

    ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable,
	expectedTable.getTableMetaData().getColumns());

    Assertion.assertEquals(expectedTable, filteredTable);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testShouldUpdateBean() throws Exception {
    // when
    dao.update(updateBean);

    // then
    IDataSet dbDataSet = getConnection().createDataSet();
    ITable actualTable = dbDataSet.getTable(tableName);

    IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
	new File(updateDataset));
    ITable expectedTable = expectedDataSet.getTable(tableName);

    ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable,
	expectedTable.getTableMetaData().getColumns());

    Assertion.assertEquals(expectedTable, filteredTable);
  }

  @Test
  public void testShouldDeleteBean() throws Exception {
    // when
    dao.delete(deleteId);

    // then
    IDataSet dbDataSet = getConnection().createDataSet();
    ITable actualTable = dbDataSet.getTable(tableName);

    IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
	new File(deleteDataset));
    ITable expectedTable = expectedDataSet.getTable(tableName);

    ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable,
	expectedTable.getTableMetaData().getColumns());

    Assertion.assertEquals(expectedTable, filteredTable);
  }
  
  @Test
  public void testShouldReturnNullForWrongId() throws Exception {
    // when
    Serializable bean = dao.getById(123456);

    // then
    Assert.assertNull(bean);
  }
}
