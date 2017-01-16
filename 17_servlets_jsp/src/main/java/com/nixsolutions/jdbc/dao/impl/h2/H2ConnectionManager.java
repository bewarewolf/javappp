package com.nixsolutions.jdbc.dao.impl.h2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

import com.nixsolutions.jdbc.dao.DAOFactory;

public class H2ConnectionManager {

  private static volatile JdbcConnectionPool pool;

  private static final Logger LOG = LogManager.getLogger();

  private static String resolveValueWithEnvVars(String value) {
    if (null == value) {
        return null;
     }

    Pattern p = Pattern.compile("\\$\\{(\\w+)\\}|\\$(\\w+)");
    Matcher m = p.matcher(value);
    StringBuffer sb = new StringBuffer();
    while (m.find()) {
        String envVarName = null == m.group(1) ? m.group(2) : m.group(1);
        String envVarValue = System.getenv(envVarName);
        m.appendReplacement(sb,
            null == envVarValue ? "" : Matcher.quoteReplacement(envVarValue));
     }
    m.appendTail(sb);
    return sb.toString();
  }

  public static Connection getConnection() throws SQLException {
    JdbcConnectionPool localPool = pool;

    if (localPool == null) {
      try (InputStream fis = H2ConnectionManager.class.getClassLoader().getResourceAsStream("jdbc.properties")) {
	Properties prop = new Properties();
	prop.load(fis);

	String strDriver = prop.getProperty("db.driver.h2");
	String strDbFileName = prop.getProperty("db.filename");
	
	URL resource = H2ConnectionManager.class.getClassLoader().getResource(strDbFileName);
	String strFullPath = Paths.get(resource.toURI()).toFile().getAbsolutePath();
	
	String strUrl = String.format(prop.getProperty("db.url.h2"), strFullPath.replace(".mv.db", ""));
	String strUsername = prop.getProperty("db.username.h2");
	String strPassword = prop.getProperty("db.password.h2");
	String strMaxConnections = prop.getProperty("db.maxConnections.h2");
	String strTimeout = prop.getProperty("db.timeout.h2");

	synchronized (H2ConnectionManager.class) {
	  localPool = pool;
	  if (localPool == null) {
	    Class.forName(strDriver);
	    localPool = pool = JdbcConnectionPool.create(strUrl, strUsername, strPassword);
	    pool.setLoginTimeout(Integer.valueOf(strTimeout));
	    pool.setMaxConnections(Integer.valueOf(strMaxConnections));
	  }
	}
      } catch (IOException | ClassNotFoundException | URISyntaxException ex) {
	LOG.error(ex);
      }
    }

    return pool.getConnection();
  }
}
