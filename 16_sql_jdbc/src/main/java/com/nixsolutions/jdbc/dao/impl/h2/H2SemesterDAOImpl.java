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

import com.nixsolutions.jdbc.bean.Semester;
import com.nixsolutions.jdbc.dao.SemesterDAO;

public class H2SemesterDAOImpl implements SemesterDAO{

  private static final Logger LOG = LogManager.getLogger();
  
  @Override
  public boolean create(Semester bean) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("INSERT INTO semester (semester_name, semester_date_start, semester_date_end) VALUES (?, ?, ?)");
      stat.setString(1, bean.getSemesterName());
      stat.setDate(2, java.sql.Date.valueOf(bean.getStartDate()));
      stat.setDate(3, java.sql.Date.valueOf(bean.getEndDate()));
            
      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add semester [%s]", bean.toString()), ex);
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
    return false;
  }

  @Override
  public boolean delete(Integer id) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("DELETE FROM semester where semester_id = ?");
      stat.setInt(1, id);
            
      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error("Can't delete semester", ex);
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
    return false;
  }

  @Override
  public Semester getById(Integer id) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM semester where semester_id = ?");
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      Semester sem = new Semester();
      while (res.next()) {
	sem.setId(res.getInt("semester_id"));
	sem.setSemesterName(res.getString("semester_name"));
	sem.setStartDate(res.getDate("semester_date_start").toLocalDate());
	sem.setEndDate(res.getDate("semester_date_end").toLocalDate());
      }
      
      return sem;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get semester [id = %d]", id), ex);
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
    return null;
  }

  @Override
  public List<Semester> getAll() {
    Connection conn = null;
    Statement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.createStatement();
      ResultSet res = stat.executeQuery("SELECT * FROM semester");
      
      List<Semester> out = new ArrayList<>();
      while (res.next()) {
	Semester sem = new Semester();
	
	sem.setId(res.getInt("semester_id"));
	sem.setSemesterName(res.getString("semester_name"));
	sem.setStartDate(res.getDate("semester_date_start").toLocalDate());
	sem.setEndDate(res.getDate("semester_date_end").toLocalDate());
	
	out.add(sem);
      }
      
      return out;
    } catch (SQLException ex) {
      
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
    return null;
  }

  @Override
  public Semester getByName(String semesterName) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM semester where semester_name = ?");
      stat.setString(1, semesterName);
      ResultSet res = stat.executeQuery();
      
      Semester sem = new Semester();
      while (res.next()) {
	sem.setId(res.getInt("semester_id"));
	sem.setSemesterName(res.getString("semester_name"));
	sem.setStartDate(res.getDate("semester_date_start").toLocalDate());
	sem.setEndDate(res.getDate("semester_date_end").toLocalDate());
      }
      
      return sem;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get semester [name = %d]", semesterName), ex);
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
    return null;
  }

  @Override
  public Semester currentSemester() {
    Connection conn = null;
    Statement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.createStatement();
      ResultSet res = stat.executeQuery("SELECT * FROM semester WHERE CURRENT_DATE() BETWEEN semester_date_start AND semester_date_end");
      
      Semester sem = new Semester();
      while (res.next()) {
	sem.setId(res.getInt("semester_id"));
	sem.setSemesterName(res.getString("semester_name"));
	sem.setStartDate(res.getDate("semester_date_start").toLocalDate());
	sem.setEndDate(res.getDate("semester_date_end").toLocalDate());
      }
      
      return sem;
    } catch (SQLException ex) {
      LOG.error("Can't get current semester", ex);
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
    return null;
  }

}
