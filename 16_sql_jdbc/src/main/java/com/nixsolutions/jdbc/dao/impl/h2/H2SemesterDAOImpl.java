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
import com.nixsolutions.jdbc.bean.Semester;
import com.nixsolutions.jdbc.dao.SemesterDAO;

public class H2SemesterDAOImpl implements SemesterDAO{

  private static final Logger LOG = LogManager.getLogger();
  
  @Override
  public int create(Semester bean) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("INSERT INTO semester (semester_name, semester_date_start, semester_date_end) VALUES (?, ?, ?)");
      stat.setString(1, bean.getSemesterName());
      stat.setDate(2, java.sql.Date.valueOf(bean.getStartDate()));
      stat.setDate(3, java.sql.Date.valueOf(bean.getEndDate()));
      stat.executeUpdate();
      
      ResultSet res = stat.getGeneratedKeys(); 
      
      while (res.next()) {
        return res.getInt(1);
      }
      
      return -1;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add semester [%s]", bean.toString()), ex);
      return -1;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public boolean update(Semester bean) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {      
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("UPDATE semester SET "
          + "semester_name = ?, "
          + "semester_date_start = ? "
          + "semester_date_end = ? "
          + "WHERE semester_id = ?");
      stat.setString(1, bean.getSemesterName());
      stat.setDate(2, java.sql.Date.valueOf(bean.getStartDate()));
      stat.setDate(3, java.sql.Date.valueOf(bean.getEndDate()));
      stat.setInt(4, bean.getId());
      
      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add grade [%s]", bean.toString()), ex);
      return false;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
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
      return false;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
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
      return null;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
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
      LOG.error("Can't get list of semesters", ex);
      return null;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
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
      return null;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
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
      return null;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

}
