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

import com.nixsolutions.jdbc.bean.Grade;
import com.nixsolutions.jdbc.dao.GradeDAO;

public class H2GradeDAOImpl implements GradeDAO {

  private static final Logger LOG = LogManager.getLogger();
    
  @Override
  public int create(Grade bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {      
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("INSERT INTO grade (description, value) VALUES (?, ?)");
      stat.setString(1, bean.getDescription());
      stat.setInt(2, bean.getValue());
      stat.executeUpdate();
      
      ResultSet res = stat.getGeneratedKeys(); 
      
      if (res.next()) {
        return res.getInt(1);
      }
      
      return -1;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add grade [%s]", bean.toString()), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public boolean update(Grade bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {      
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("UPDATE grade SET "
          + "description = ?, "
          + "value = ? "
          + "WHERE grade_id = ?");
      stat.setString(1, bean.getDescription());
      stat.setInt(2, bean.getValue());
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
      stat = conn.prepareStatement("DELETE FROM grade where grade_id = ?");
      stat.setInt(1, id);
            
      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error("Can't delete grade", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  protected Grade processRecord(ResultSet res) throws SQLException {
    Grade gr = new Grade();
    gr.setId(res.getInt("grade_id"));
    gr.setDescription(res.getString("description"));
    gr.setValue(res.getInt("value"));
    
    return gr;
  }
  
  @Override
  public Grade getById(Integer id) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM grade where grade_id = ?");
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      if (res.next()) {
	return processRecord(res);
      }
      
      return null;
    } catch (SQLException ex) {
      LOG.error("Can't get grade [id = " + id + "]", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Grade> getAll() throws SQLException {
    Connection conn = null;
    Statement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.createStatement();
      ResultSet res = stat.executeQuery("SELECT * FROM grade");
      
      List<Grade> out = new ArrayList<>();
      while (res.next()) {
	out.add(processRecord(res));
      }
      
      return out;
    } catch (SQLException ex) {
      LOG.error("Can't get list of grades", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public Grade getByDescription(String description) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM grade where description = ?");
      stat.setString(1, description);
      ResultSet res = stat.executeQuery();
      
      if (res.next()) {
	return processRecord(res);
      }
      
      return null;
    } catch (SQLException ex) {
      LOG.error("Can't get grade [description = " + description + "]", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }
}
