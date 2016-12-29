package com.nixsolutions.jdbc.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DropSchemaApp {

  private static final Logger LOG = LogManager.getLogger();

  public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {

    LOG.info("Starting DROP SCHEMA application");
    
    try {
      Class.forName("org.h2.Driver");
    } catch (ClassNotFoundException ex) {
      LOG.error("Can't load driver", ex);
    }

    Connection conn = null;
    Statement st = null;

    try {
      FileInputStream fis = new FileInputStream("jdbc.properties");
      Properties prop = new Properties();
      prop.load(fis);

      String strUrl = prop.getProperty("db.url");
      String strDbName = prop.getProperty("db.name");
      String strUsername = prop.getProperty("db.username");
      String strPassword = prop.getProperty("db.password");

      conn = DriverManager.getConnection(strUrl + "~/" + strDbName, strUsername, strPassword);
      st = conn.createStatement();

      st.addBatch("DROP TABLE semester");
      st.addBatch("DROP TABLE grade");
      st.addBatch("DROP TABLE person");
      st.addBatch("DROP TABLE subject");
      st.addBatch("DROP TABLE journal");
      st.addBatch("DROP TABLE phone_number");
      st.addBatch("DROP TABLE person_type");
      st.addBatch("DROP TABLE person_status");

      st.executeBatch();
      
      LOG.info("Schema has been dropped");
    } catch (SQLException | IOException ex) {
      LOG.error("Can't drop schema", ex);
      throw ex;
    } finally {
      try {
	if (st != null) {
	  st.close();
	}
      } catch (SQLException ex) {
	LOG.error("Can't close statement", ex);
      }
      try {
	if (conn != null) {
	  conn.close();
	}
      } catch (SQLException ex) {
	LOG.error("Can't close connection", ex);
      }
    }
  }
}
