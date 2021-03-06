package com.nixsolutions.jdbc.dao.impl.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.jdbc.bean.Journal;
import com.nixsolutions.jdbc.dao.JournalDAO;

public class H2JournalDAOImpl implements JournalDAO {

  private static final Logger LOG = LogManager.getLogger();

  @Override
  public int create(Journal bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement(
	  "INSERT INTO journal (person_id, subject_id, grade_id, grade_date) " + "VALUES (?, ?, ?, ?)");
      stat.setInt(1, bean.getPersonId());
      stat.setInt(2, bean.getSubjectId());
      stat.setInt(3, bean.getGradeId());
      stat.setDate(4, bean.getGradeDate() == null 
          ? java.sql.Date.valueOf(LocalDate.now())
          : java.sql.Date.valueOf(bean.getGradeDate()));
      stat.executeUpdate();

      ResultSet res = stat.getGeneratedKeys();

      if (res.next()) {
	return res.getInt(1);
      }

      return -1;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add journal resord [%s]", bean.toString()), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public boolean update(Journal bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("UPDATE journal SET " + "person_id = ?, " + "subject_id = ?, " + "grade_id = ?, "
	  + "grade_date = ? " + "WHERE record_id = ?");
      stat.setInt(1, bean.getPersonId());
      stat.setInt(2, bean.getSubjectId());
      stat.setInt(3, bean.getGradeId());
      stat.setDate(4, java.sql.Date.valueOf(bean.getGradeDate()));
      stat.setInt(5, bean.getId());

      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't update journal [%s]", bean.toString()), ex);
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
      stat = conn.prepareStatement("DELETE FROM journal where record_id = ?");
      stat.setInt(1, id);

      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error("Can't delete journal record", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  protected Journal processRecord(ResultSet res) throws SQLException {
    Journal rec = new Journal();

    rec.setId(res.getInt("record_id"));
    rec.setGradeDate(res.getDate("grade_date").toLocalDate());
    rec.setPersonId(res.getInt("person_id"));
    rec.setGradeId(res.getInt("grade_id"));
    rec.setSubjectId(res.getInt("subject_id"));
    
    return rec;
  }
  
  @Override
  public Journal getById(Integer id) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM journal where record_id = ?");
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();

      if (res.next()) {	
	return processRecord(res);
      }

      return null;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get journal record [id = %d]", id), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Journal> getAll() throws SQLException {
    Connection conn = null;
    Statement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.createStatement();
      ResultSet res = stat.executeQuery("SELECT * FROM journal");

      List<Journal> out = new ArrayList<>();
      while (res.next()) {
	out.add(processRecord(res));
      }

      return out;
    } catch (SQLException ex) {
      LOG.error("Can't get journal records", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Journal> getBySubjectId(Integer subjectId) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM journal WHERE subject_id = ?");
      stat.setInt(1, subjectId);
      ResultSet res = stat.executeQuery();

      List<Journal> out = new ArrayList<>();
      while (res.next()) {
	out.add(processRecord(res));
      }

      return out;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get journal record for subject [id = %d]", subjectId), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Journal> getByPersonId(Integer personId) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM journal where person_id = ?");
      stat.setInt(1, personId);
      ResultSet res = stat.executeQuery();

      List<Journal> out = new ArrayList<>();
      while (res.next()) {	
	out.add(processRecord(res));
      }

      return out;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get journal record for person [id = %d]", personId), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }
}
