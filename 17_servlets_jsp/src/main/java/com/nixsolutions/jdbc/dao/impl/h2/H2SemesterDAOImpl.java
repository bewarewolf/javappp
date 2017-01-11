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

import com.nixsolutions.jdbc.bean.Semester;
import com.nixsolutions.jdbc.dao.SemesterDAO;

public class H2SemesterDAOImpl implements SemesterDAO {

  private static final Logger LOG = LogManager.getLogger();

  @Override
  public int create(Semester bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement(
	  "INSERT INTO semester (semester_name, semester_date_start, semester_date_end) VALUES (?, ?, ?)");
      stat.setString(1, bean.getSemesterName());
      stat.setDate(2, java.sql.Date.valueOf(bean.getStartDate()));
      stat.setDate(3, java.sql.Date.valueOf(bean.getEndDate()));
      stat.executeUpdate();

      ResultSet res = stat.getGeneratedKeys();

      if (res.next()) {
	return res.getInt(1);
      }

      return -1;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add semester [%s]", bean.toString()), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public boolean update(Semester bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("UPDATE semester SET " + "semester_name = ?, " + "semester_date_start = ?, "
	  + "semester_date_end = ? " + "WHERE semester_id = ?");
      stat.setString(1, bean.getSemesterName());
      stat.setDate(2, java.sql.Date.valueOf(bean.getStartDate()));
      stat.setDate(3, java.sql.Date.valueOf(bean.getEndDate()));
      stat.setInt(4, bean.getId());

      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't update semester [%s]", bean.toString()), ex);
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
      stat = conn.prepareStatement("DELETE FROM semester where semester_id = ?");
      stat.setInt(1, id);

      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error("Can't delete semester", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  protected Semester processRecord(ResultSet res) throws SQLException {
    Semester sem = new Semester();
    sem.setId(res.getInt("semester_id"));
    sem.setSemesterName(res.getString("semester_name"));
    sem.setStartDate(res.getDate("semester_date_start").toLocalDate());
    sem.setEndDate(res.getDate("semester_date_end").toLocalDate());

    return sem;
  }

  @Override
  public Semester getById(Integer id) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM semester where semester_id = ?");
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();

      if (res.next()) {
	return processRecord(res);
      }

      return null;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get semester [id = %d]", id), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Semester> getAll() throws SQLException {
    Connection conn = null;
    Statement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.createStatement();
      ResultSet res = stat.executeQuery("SELECT * FROM semester");

      List<Semester> out = new ArrayList<>();
      while (res.next()) {
	out.add(processRecord(res));
      }

      return out;
    } catch (SQLException ex) {
      LOG.error("Can't get list of semesters", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public Semester getByName(String semesterName) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM semester where semester_name = ?");
      stat.setString(1, semesterName);
      ResultSet res = stat.executeQuery();

      if (res.next()) {
	return processRecord(res);
      }

      return null;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get semester [name = %d]", semesterName), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public Semester currentSemester() throws SQLException {
    Connection conn = null;
    Statement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.createStatement();
      ResultSet res = stat.executeQuery(
	  "SELECT * FROM semester WHERE CURRENT_DATE() BETWEEN semester_date_start AND semester_date_end");

      if (res.next()) {
	return processRecord(res);
      }

      return null;
    } catch (SQLException ex) {
      LOG.error("Can't get current semester", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }
}
