package com.nixsolutions.jdbc.dao.impl.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.jdbc.bean.PersonStatus;
import com.nixsolutions.jdbc.dao.PersonStatusDAO;

public class H2PersonStatusDAOImpl implements PersonStatusDAO {

  private static final Logger LOG = LogManager.getLogger();
  
  @Override
  public int create(PersonStatus bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("INSERT INTO person_status (description, value) VALUES (?, ?)");
      stat.setString(1, bean.getDescription());
      stat.setString(2, bean.getValue());            
      stat.executeUpdate();
      
      ResultSet res = stat.getGeneratedKeys(); 
      
      if (res.next()) {
        return res.getInt(1);
      }
      
      return -1;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add person status [%s]", bean.toString()), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public boolean update(PersonStatus bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {      
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("UPDATE person_status SET "
          + "description = ?, "
          + "value = ? "
          + "WHERE person_status_id = ?");
      stat.setString(1, bean.getDescription());
      stat.setString(2, bean.getValue());
      stat.setInt(3, bean.getId());
      
      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add grade [%s]", bean.toString()), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }
  
  @Override
  public boolean delete(Integer id) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("DELETE FROM person_status where person_status_id = ?");
      stat.setInt(1, id);
            
      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error("Can't delete person status", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  protected PersonStatus processRecord(ResultSet res) throws SQLException {
    PersonStatus ps = new PersonStatus();
    ps.setId(res.getInt("person_status_id"));
    ps.setDescription(res.getString("description"));
    ps.setValue(res.getString("value"));
    
    return ps;
  }
  
  @Override
  public PersonStatus getById(Integer id) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM person_status where person_status_id = ?");
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
            
      if (res.next()) {
	return processRecord(res);
      }
      
      return null;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get person status [id = %d]", id), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<PersonStatus> getAll() throws SQLException {
    Connection conn = null;
    Statement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.createStatement();
      ResultSet res = stat.executeQuery("SELECT * FROM person_status");
      
      List<PersonStatus> out = new ArrayList<>();
      while (res.next()) {
	out.add(processRecord(res));
      }
      
      return out;
    } catch (SQLException ex) {
      LOG.error("Can't get list of person status", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public PersonStatus getByValue(String value) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM person_status where value = ?");
      stat.setString(1, value);
      ResultSet res = stat.executeQuery();
            
      if (res.next()) {
	return processRecord(res);
      }
      
      return null;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get person status [value = %d]", value), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }
}
