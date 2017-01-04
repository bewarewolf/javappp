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
import com.nixsolutions.jdbc.bean.Journal;
import com.nixsolutions.jdbc.dao.JournalDAO;

public class H2JournalDAOImpl implements JournalDAO {

  private static final Logger LOG = LogManager.getLogger();

  @Override
  public int create(Journal bean) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("INSERT INTO journal (person_id, subject_id, grade_id) VALUES (?, ?, ?)");
      stat.setInt(1, bean.getPerson().getId());
      stat.setInt(2, bean.getSubject().getId());
      stat.setInt(2, bean.getGrade().getId());
      stat.executeUpdate();
      
      ResultSet res = stat.getGeneratedKeys(); 
      
      while (res.next()) {
        return res.getInt(1);
      }
      
      return -1;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add journal resord [%s]", bean.toString()), ex);
      return -1;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public boolean update(Journal bean) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {      
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("UPDATE journal SET "
          + "person_id = ?, "
          + "subject_id = ? "
          + "grade_id = ? "
          + "WHERE record_id = ?");
      stat.setInt(1, bean.getPerson().getId());
      stat.setInt(2, bean.getSubject().getId());
      stat.setInt(3, bean.getGrade().getId());
      stat.setInt(4, bean.getId());
      stat.executeUpdate();
      
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
      stat = conn.prepareStatement("DELETE FROM journal where record_id = ?");
      stat.setInt(1, id);

      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error("Can't delete journal record", ex);
      return false;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }    
  }

  @Override
  public Journal getById(Integer id) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM journal where record_id = ?");
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();

      Journal rec = new Journal();
      while (res.next()) {
	rec.setId(res.getInt("record_id"));
	rec.setGradeDate(res.getDate("grade_date").toLocalDate());

	H2PersonDAOImpl persDao = new H2PersonDAOImpl();
	rec.setPerson(persDao.getById(res.getInt("person_id")));

	H2GradeDAOImpl gradeDao = new H2GradeDAOImpl();
	rec.setGrade(gradeDao.getById(res.getInt("grade_id")));

	H2SubjectDAOImpl subjDao = new H2SubjectDAOImpl();
	rec.setSubject(subjDao.getById(res.getInt("subject_id")));
      }

      return rec;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get journal record [id = %d]", id), ex);
      return null;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Journal> getAll() {
    Connection conn = null;
    Statement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.createStatement();
      ResultSet res = stat.executeQuery("SELECT * FROM journal");

      List<Journal> out = new ArrayList<>();
      while (res.next()) {
	Journal rec = new Journal();

	rec.setId(res.getInt("record_id"));
	rec.setGradeDate(res.getDate("grade_date").toLocalDate());

	H2PersonDAOImpl persDao = new H2PersonDAOImpl();
	rec.setPerson(persDao.getById(res.getInt("person_id")));

	H2GradeDAOImpl gradeDao = new H2GradeDAOImpl();
	rec.setGrade(gradeDao.getById(res.getInt("grade_id")));

	H2SubjectDAOImpl subjDao = new H2SubjectDAOImpl();
	rec.setSubject(subjDao.getById(res.getInt("subject_id")));

	out.add(rec);
      }

      return out;
    } catch (SQLException ex) {
      LOG.error("Can't get journal records", ex);
      return null;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Journal> getBySubjectId(Integer subjectId) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM journal where subject_id = ?");
      stat.setInt(1, subjectId);
      ResultSet res = stat.executeQuery();

      List<Journal> out = new ArrayList<>();
      while (res.next()) {
	Journal rec = new Journal();

	rec.setId(res.getInt("record_id"));
	rec.setGradeDate(res.getDate("grade_date").toLocalDate());

	H2PersonDAOImpl persDao = new H2PersonDAOImpl();
	rec.setPerson(persDao.getById(res.getInt("person_id")));

	H2GradeDAOImpl gradeDao = new H2GradeDAOImpl();
	rec.setGrade(gradeDao.getById(res.getInt("grade_id")));

	H2SubjectDAOImpl subjDao = new H2SubjectDAOImpl();
	rec.setSubject(subjDao.getById(res.getInt("subject_id")));

	out.add(rec);
      }
      
      return out;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get journal record for subject [id = %d]", subjectId), ex);
      return null;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Journal> getByPersonId(Integer personId) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM journal where person_id = ?");
      stat.setInt(1, personId);
      ResultSet res = stat.executeQuery();

      List<Journal> out = new ArrayList<>();
      while (res.next()) {
	Journal rec = new Journal();

	rec.setId(res.getInt("record_id"));
	rec.setGradeDate(res.getDate("grade_date").toLocalDate());

	H2PersonDAOImpl persDao = new H2PersonDAOImpl();
	rec.setPerson(persDao.getById(personId));

	H2GradeDAOImpl gradeDao = new H2GradeDAOImpl();
	rec.setGrade(gradeDao.getById(res.getInt("grade_id")));

	H2SubjectDAOImpl subjDao = new H2SubjectDAOImpl();
	rec.setSubject(subjDao.getById(res.getInt("subject_id")));

	out.add(rec);
      }
      
      return out;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get journal record for person [id = %d]", personId), ex);
      return null;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

}
