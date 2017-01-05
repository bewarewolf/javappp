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

import com.nixsolutions.jdbc.bean.PhoneNumber;
import com.nixsolutions.jdbc.dao.PhoneNumberDAO;

public class H2PhoneNumberDAOImpl implements PhoneNumberDAO {

  private static final Logger LOG = LogManager.getLogger();
  
  @Override
  public int create(PhoneNumber bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("INSERT INTO phone_number (person_id, phone_number) VALUES (?, ?)");
      stat.setInt(1, bean.getPersonId());
      stat.setString(2, bean.getPhoneNumber());
      stat.executeUpdate();
      
      ResultSet res = stat.getGeneratedKeys(); 
      
      if (res.next()) {
        return res.getInt(1);
      }
      
      return -1;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add phone number [%s]", bean.toString()), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public boolean update(PhoneNumber bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {      
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("UPDATE phone_number SET "
          + "person_id = ?, "
          + "phone_number = ? "
          + "WHERE phone_number_id = ?");
      stat.setInt(1, bean.getPersonId());
      stat.setString(2, bean.getPhoneNumber());
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
      stat = conn.prepareStatement("DELETE FROM phone_number where phone_number_id = ?");
      stat.setInt(1, id);
            
      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error("Can't delete phone number", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public PhoneNumber getById(Integer id) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM phone_number where phone_number_id = ?");
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      if (res.next()) {
	PhoneNumber pn = new PhoneNumber();
	pn.setId(res.getInt("phone_number_id"));
	pn.setPersonId(res.getInt("person_id"));
	pn.setPhoneNumber(res.getString("phone_number"));
	
	return pn;
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
  public List<PhoneNumber> getAll() throws SQLException {
    Connection conn = null;
    Statement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.createStatement();
      ResultSet res = stat.executeQuery("SELECT * FROM phone_number");
      
      List<PhoneNumber> out = new ArrayList<>();
      while (res.next()) {
	PhoneNumber pn = new PhoneNumber();
	
	pn.setId(res.getInt("phone_number_id"));
	pn.setPersonId(res.getInt("person_id"));
	pn.setPhoneNumber(res.getString("phone_number"));
	
	out.add(pn);
      }
      
      return out;
    } catch (SQLException ex) {
      LOG.error("Can't get list of phone numbers", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public boolean deleteByPersonId(Integer id) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("DELETE FROM phone_number where person_id = ?");
      stat.setInt(1, id);
            
      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't delete phone numbers for person [id = %d]", id), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<PhoneNumber> getByPersonId(Integer id) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM phone_number where person_id = ?");
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      List<PhoneNumber> out = new ArrayList<>();
      while (res.next()) {
	PhoneNumber pn = new PhoneNumber();
	
	pn.setId(res.getInt("phone_number_id"));
	pn.setPersonId(res.getInt("person_id"));
	pn.setPhoneNumber(res.getString("phone_number"));
	
	out.add(pn);
      }
      
      return out;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get phone numbers for person [id = %d]", id), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }
}
