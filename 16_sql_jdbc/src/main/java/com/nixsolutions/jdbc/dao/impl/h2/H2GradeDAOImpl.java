package com.nixsolutions.jdbc.dao.impl.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.jdbc.bean.Grade;
import com.nixsolutions.jdbc.dao.GradeDAO;

public class H2GradeDAOImpl implements GradeDAO {

  private static final Logger LOG = LogManager.getLogger();
  
  private Connection conn;
  
  public H2GradeDAOImpl() throws SQLException {
    this.conn = H2ConnectionManager.getConnection();
  }
  
  public H2GradeDAOImpl(Connection conn) throws SQLException {
    this.conn = conn;
  }
  
  @Override
  public boolean create(Grade bean) throws SQLException {
    //Connection conn = null;
    PreparedStatement stat = null;
    try {      
      stat = conn.prepareStatement("INSERT INTO grade (description, value) VALUES (?, ?)");
      stat.setString(1, bean.getDescription());
      stat.setInt(2, bean.getValue());
            
      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add grade [%s]", bean.toString()), ex);
      throw ex;
    } finally {
      try {
	if (stat != null) {
	  stat.close();
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
      try {
	if (stat != null) {
	  stat.close();
	}
      } catch (SQLException ex) {
	LOG.error("Can't close statement", ex);
	throw ex;
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

  @Override
  public Grade getById(Integer id) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM grade where grade_id = ?");
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      Grade gr = new Grade();
      while (res.next()) {
	gr.setId(res.getInt("grade_id"));
	gr.setDescription(res.getString("description"));
	gr.setValue(res.getInt("value"));
      }
      
      return gr;
    } catch (SQLException ex) {
      LOG.error("Can't get grade [id = " + id + "]", ex);
      throw ex;
    } finally {
      try {
	if (stat != null) {
	  stat.close();
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
	Grade gr = new Grade();
	
	gr.setId(res.getInt("grade_id"));
	gr.setDescription(res.getString("description"));
	gr.setValue(res.getInt("value"));
	
	out.add(gr);
      }
      
      return out;
    } catch (SQLException ex) {
      LOG.error("Can't get list of grades", ex);
      throw ex;
    } finally {
      try {
	if (stat != null) {
	  stat.close();
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
