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

import com.nixsolutions.jdbc.bean.PersonType;
import com.nixsolutions.jdbc.dao.PersonTypeDAO;

public class H2PersonTypeDAOImpl implements PersonTypeDAO {

  private static final Logger LOG = LogManager.getLogger();
  
  @Override
  public int create(PersonType bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("INSERT INTO person_type (description, value) VALUES (?, ?)");
      stat.setString(1, bean.getDescription());
      stat.setString(2, bean.getValue());
      stat.executeUpdate();
      
      ResultSet res = stat.getGeneratedKeys(); 
      
      if (res.next()) {
        return res.getInt(1);
      }
      
      return -1;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add person type [%s]", bean.toString()), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public boolean update(PersonType bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {      
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("UPDATE person_type SET "
          + "description = ?, "
          + "value = ? "
          + "WHERE person_type_id = ?");
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
      stat = conn.prepareStatement("DELETE FROM person_type where person_type_id = ?");
      stat.setInt(1, id);
            
      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error("Can't delete person type", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  protected PersonType processRecord(ResultSet res) throws SQLException {
    PersonType pt = new PersonType();
    pt.setId(res.getInt("person_type_id"));
    pt.setDescription(res.getString("description"));
    pt.setValue(res.getString("value"));
    
    return pt;
  }
  
  @Override
  public PersonType getById(Integer id) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM person_type where person_type_id = ?");
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      if (res.next()) {
	return processRecord(res);
      }
      
      return null;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get person type [id = %d]", id), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<PersonType> getAll() throws SQLException {
    Connection conn = null;
    Statement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.createStatement();
      ResultSet res = stat.executeQuery("SELECT * FROM person_type");
      
      List<PersonType> out = new ArrayList<>();
      while (res.next()) {
	out.add(processRecord(res));
      }
      
      return out;
    } catch (SQLException ex) {
      LOG.error("Can't get list of person type", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public PersonType getByValue(String value) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM person_type where value = ?");
      stat.setString(1, value);
      ResultSet res = stat.executeQuery();
      
      if (res.next()) {
	return processRecord(res);
      }
      
      return null;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get person type [value = %d]", value), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }
}
