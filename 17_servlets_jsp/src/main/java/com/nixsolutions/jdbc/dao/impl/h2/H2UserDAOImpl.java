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

import com.nixsolutions.jdbc.bean.User;
import com.nixsolutions.jdbc.dao.UserDAO;

public class H2UserDAOImpl implements UserDAO {

  private static final Logger LOG = LogManager.getLogger();
  
  @Override
  public int create(User bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {      
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("INSERT INTO user (person_id, login, password, role_id) VALUES (?, ?, ?, ?)");
      stat.setObject(1, bean.getPersonId(), java.sql.Types.INTEGER);
      stat.setString(2, bean.getLogin());
      stat.setString(3, bean.getPassword());
      stat.setInt(4, bean.getRoleId());
      stat.executeUpdate();
      
      ResultSet res = stat.getGeneratedKeys(); 
      
      if (res.next()) {
        return res.getInt(1);
      }
      
      return -1;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add user [%s]", bean.toString()), ex);
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
      stat = conn.prepareStatement("DELETE FROM user WHERE user_id = ?");
      stat.setInt(1, id);
            
      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error("Can't delete user", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public boolean update(User bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {      
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("UPDATE user SET "
          + "person_id = ?, "
          + "login = ?, "
          + "password = ?, "
          + "role_id = ? "
          + "WHERE user_id = ?");
      stat.setObject(1, bean.getPersonId(), java.sql.Types.INTEGER);
      stat.setString(2, bean.getLogin());
      stat.setString(3, bean.getPassword());
      stat.setInt(4, bean.getRoleId());
      stat.setInt(5, bean.getId());
      
      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't update user [%s]", bean.toString()), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  protected User processRecord(ResultSet res) throws SQLException {
    User user = new User();
    user.setId(res.getInt("user_id"));
    user.setPersonId(res.getInt("person_id"));
    user.setLogin(res.getString("login"));
    user.setPassword(res.getString("password"));
    user.setRoleId(res.getInt("role_id"));
    
    return user;
  }
  
  @Override
  public User getById(Integer id) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM user where user_id = ?");
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      if (res.next()) {
	return processRecord(res);
      }
      
      return null;
    } catch (SQLException ex) {
      LOG.error("Can't get user [id = " + id + "]", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<User> getAll() throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM user");
      ResultSet res = stat.executeQuery();
      
      List<User> out = new ArrayList<>();
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
  public User getByLogin(String login) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
            
      stat = conn.prepareStatement("SELECT * FROM user where login = ?");
      stat.setString(1, login);
      ResultSet res = stat.executeQuery();
      
      if (res.next()) {
	return processRecord(res);
      }
      
      return null;
    } catch (SQLException ex) {
      LOG.error("Can't get user [login = " + login + "]", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<User> getByRoleId(Integer roleId) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM user where role_id = ?");
      stat.setInt(1, roleId);
      ResultSet res = stat.executeQuery();
      
      List<User> out = new ArrayList<>();
      while (res.next()) {
	out.add(processRecord(res));
      }
      
      return out;
    } catch (SQLException ex) {
      LOG.error("Can't get list of users [role_id = " + roleId + "]", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

}
