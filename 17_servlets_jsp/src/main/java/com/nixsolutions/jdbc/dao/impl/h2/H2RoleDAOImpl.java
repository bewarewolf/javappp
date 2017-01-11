package com.nixsolutions.jdbc.dao.impl.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.jdbc.bean.Role;
import com.nixsolutions.jdbc.dao.RoleDAO;

public class H2RoleDAOImpl implements RoleDAO {

  private static final Logger LOG = LogManager.getLogger();
  
  @Override
  public int create(Role bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {      
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("INSERT INTO role (role_name) VALUES (?)");
      stat.setString(1, bean.getRoleName());
      stat.executeUpdate();
      
      ResultSet res = stat.getGeneratedKeys(); 
      
      if (res.next()) {
        return res.getInt(1);
      }
      
      return -1;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add role [%s]", bean.toString()), ex);
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
      stat = conn.prepareStatement("DELETE FROM role WHERE role_id = ?");
      stat.setInt(1, id);
            
      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error("Can't delete role", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public boolean update(Role bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {      
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("UPDATE role SET "
          + "role_name = ? "
          + "WHERE role_id = ?");
      stat.setString(1, bean.getRoleName());
      stat.setInt(2, bean.getId());
      
      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't update user [%s]", bean.toString()), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  protected Role processRecord(ResultSet res) throws SQLException {
    Role role = new Role();
    role.setId(res.getInt("role_id"));
    role.setRoleName(res.getString("role_name"));
    
    return role;
  }
  
  @Override
  public Role getById(Integer id) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM role where role_id = ?");
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      if (res.next()) {
	return processRecord(res);
      }
      
      return null;
    } catch (SQLException ex) {
      LOG.error("Can't get role [id = " + id + "]", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Role> getAll() throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM role");
      ResultSet res = stat.executeQuery();
      
      List<Role> out = new ArrayList<>();
      while (res.next()) {
	out.add(processRecord(res));
      }
      
      return out;
    } catch (SQLException ex) {
      LOG.error("Can't get list of users", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public Role getByName(String name) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM role where role_name = ?");
      stat.setString(1, name);
      ResultSet res = stat.executeQuery();
      
      if (res.next()) {
	return processRecord(res);
      }
      
      return null;
    } catch (SQLException ex) {
      LOG.error("Can't get role [role_name = " + name + "]", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

}
