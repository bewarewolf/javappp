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

import com.nixsolutions.jdbc.bean.Subject;
import com.nixsolutions.jdbc.dao.SubjectDAO;

public class H2SubjectDAOImpl implements SubjectDAO {

  private static final Logger LOG = LogManager.getLogger();

  @Override
  public int create(Subject bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("INSERT INTO subject (subject_name, teacher_id, semester_id) VALUES (?, ?, ?)");
      stat.setString(1, bean.getSubjectName());
      stat.setInt(2, bean.getTeacherId());
      stat.setInt(3, bean.getSemesterId());
      stat.executeUpdate();

      ResultSet res = stat.getGeneratedKeys();

      if (res.next()) {
	return res.getInt(1);
      }

      return -1;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add subject [%s]", bean.toString()), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public boolean update(Subject bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("UPDATE subject SET " + "subject_name = ?, " + "teacher_id = ?, "
	  + "semester_id = ? " + "WHERE subject_id = ?");
      stat.setString(1, bean.getSubjectName());
      stat.setInt(2, bean.getTeacherId());
      stat.setInt(3, bean.getSemesterId());
      stat.setInt(4, bean.getId());

      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't update subject [%s]", bean.toString()), ex);
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
      stat = conn.prepareStatement("DELETE FROM subject where subject_id = ?");
      stat.setInt(1, id);

      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error("Can't delete subject", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  protected Subject processRecord(ResultSet res) throws SQLException {
    Subject sub = new Subject();

    sub.setId(res.getInt("subject_id"));
    sub.setSubjectName(res.getString("subject_name"));
    sub.setSemesterId(res.getInt("semester_id"));
    sub.setTeacherId(res.getInt("teacher_id"));

    return sub;
  }

  @Override
  public Subject getById(Integer id) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM subject where subject_id = ?");
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();

      if (res.next()) {
	return processRecord(res);
      }

      return null;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get subject [id = %d]", id), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Subject> getAll() throws SQLException {
    Connection conn = null;
    Statement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.createStatement();
      ResultSet res = stat.executeQuery("SELECT * FROM subject");

      List<Subject> out = new ArrayList<>();
      while (res.next()) {
	out.add(processRecord(res));
      }

      return out;
    } catch (SQLException ex) {
      LOG.error("Can't get list of subjects", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Subject> getByTeacherId(Integer teacherId) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM subject where teacher_id = ?");
      stat.setInt(1, teacherId);
      ResultSet res = stat.executeQuery();

      List<Subject> out = new ArrayList<>();
      while (res.next()) {
	out.add(processRecord(res));
      }

      return out;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get subjects for teacher [id = %d]", teacherId), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Subject> getBySemesterId(Integer semesterId) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM subject where semester_id = ?");
      stat.setInt(1, semesterId);
      ResultSet res = stat.executeQuery();

      List<Subject> out = new ArrayList<>();
      while (res.next()) {
	out.add(processRecord(res));
      }

      return out;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get subjects for semester [id = %d]", semesterId), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public Subject getByName(String name) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM subject where subject_name = ?");
      stat.setString(1, name);
      ResultSet res = stat.executeQuery();

      if (res.next()) {
	return processRecord(res);
      }

      return null;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get subject [name = %d]", name), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }
}
