package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

public abstract class BaseTest extends DBTestCase {

  private static final Logger LOG = LogManager.getLogger();
  
  protected IDatabaseTester dbTest;
  
  public BaseTest(String name) throws Exception {
    super(name);
    
    try (FileInputStream fis = new FileInputStream("jdbc.properties")) {
      Properties prop = new Properties();
      prop.load(fis);

      System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, prop.getProperty("db.driver.h2")); 
          
      System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, prop.getProperty("db.url.h2")
          + "~/" + prop.getProperty("db.name.h2"));
      System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, prop.getProperty("db.username.h2"));
      System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, prop.getProperty("db.password.h2"));
      System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, prop.getProperty("db.name.h2"));
      
//      dbTest = new JdbcDatabaseTester(prop.getProperty("db.driver.h2"),
//          prop.getProperty("db.url.h2") + "~/" + prop.getProperty("db.name.h2"),
//          prop.getProperty("db.username.h2"),
//          prop.getProperty("db.password.h2"));
//      dbTest.setDataSet(getDataSet());
//      dbTest.onSetup();
    } catch (IOException ex) {
      LOG.error(ex);
    }
  }  
  
  @Override
  protected DatabaseOperation getSetUpOperation() throws Exception {
      return DatabaseOperation.CLEAN_INSERT;
  }

  @Override
  protected DatabaseOperation getTearDownOperation() throws Exception {
      return DatabaseOperation.NONE;
  }
}
