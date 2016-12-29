package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

public class H2ConnectionManager {

  private static volatile JdbcConnectionPool pool;

  private static final Logger LOG = LogManager.getLogger();

  public static Connection getConnection() throws SQLException {
    JdbcConnectionPool localPool = pool;
    
    if (localPool == null) {
      try (FileInputStream fis = new FileInputStream("jdbc.properties")) {
	Properties prop = new Properties();
	prop.load(fis);

	String strUrl = prop.getProperty("db.url");
	String strDbName = prop.getProperty("db.name");
	String strUsername = prop.getProperty("db.username");
	String strPassword = prop.getProperty("db.password");
	String strMaxConnections = prop.getProperty("db.maxConnections");
	String strTimeout = prop.getProperty("db.timeout");

	synchronized(H2ConnectionManager.class) {
	  localPool = pool;
	  if (localPool == null) {
	    localPool = pool = JdbcConnectionPool.create(strUrl + "~/" + strDbName, strUsername, strPassword);
	    pool.setLoginTimeout(Integer.valueOf(strTimeout));
      	    pool.setMaxConnections(Integer.valueOf(strMaxConnections));
	  }
	}
      } catch (IOException ex) {
	LOG.error("", ex);
      }
    }

    return pool.getConnection();
  }
}
