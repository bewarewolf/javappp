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
import com.nixsolutions.jdbc.bean.Subject;
import com.nixsolutions.jdbc.dao.SubjectDAO;

public class H2SubjectDAOImpl implements SubjectDAO {

  private static final Logger LOG = LogManager.getLogger();
  
  @Override
  public int create(Subject bean) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("INSERT INTO subject (subject_name, teacher_id, semester_id) VALUES (?, ?, ?)");
      stat.setString(1, bean.getSubjectName());
      stat.setInt(2, bean.getTeacher().getId());
      stat.setInt(2, bean.getSemester().getId());
      stat.executeUpdate();
      
      ResultSet res = stat.getGeneratedKeys(); 
      
      while (res.next()) {
        return res.getInt(1);
      }
      
      return -1;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add subject [%s]", bean.toString()), ex);
      return -1;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public boolean update(Subject bean) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {      
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("UPDATE subject SET "
          + "subject_name = ?, "
          + "teacher_id = ? "
          + "semester_id = ? "
          + "WHERE subject_id = ?");
      stat.setString(1, bean.getSubjectName());
      stat.setInt(2, bean.getTeacher().getId());
      stat.setInt(3, bean.getSemester().getId());
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
      stat = conn.prepareStatement("DELETE FROM subject where subject_id = ?");
      stat.setInt(1, id);
            
      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error("Can't delete subject", ex);
      return false;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public Subject getById(Integer id) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM subject where subject_id = ?");
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      Subject sub = new Subject();
      while (res.next()) {
	sub.setId(res.getInt("subject_id"));
	sub.setSubjectName(res.getString("subject_name"));
	
	H2SemesterDAOImpl semDao = new H2SemesterDAOImpl();
	sub.setSemester(semDao.getById(res.getInt("semester_id")));
	
	H2PersonDAOImpl persDao = new H2PersonDAOImpl();
	sub.setTeacher(persDao.getById(res.getInt("teacher_id")));
      }
      
      return sub;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get subject [id = %d]", id), ex);
      return null;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Subject> getAll() {
    Connection conn = null;
    Statement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.createStatement();
      ResultSet res = stat.executeQuery("SELECT * FROM subject");
      
      List<Subject> out = new ArrayList<>();
      while (res.next()) {
	Subject sub = new Subject();
	
	sub.setId(res.getInt("subject_id"));
	sub.setSubjectName(res.getString("subject_name"));
	
	H2SemesterDAOImpl semDao = new H2SemesterDAOImpl();
	sub.setSemester(semDao.getById(res.getInt("semester_id")));
	
	H2PersonDAOImpl persDao = new H2PersonDAOImpl();
	sub.setTeacher(persDao.getById(res.getInt("teacher_id")));
	
	out.add(sub);
      }
      
      return out;
    } catch (SQLException ex) {
      LOG.error("Can't get list of subjects", ex);
      return null;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Subject> getByTeacherId(Integer teacherId) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM subject where teacher_id = ?");
      stat.setInt(1, teacherId);
      ResultSet res = stat.executeQuery();
      
      List<Subject> out = new ArrayList<>();
      while (res.next()) {
	Subject sub = new Subject();
	
	sub.setId(res.getInt("subject_id"));
	sub.setSubjectName(res.getString("subject_name"));
	
	H2SemesterDAOImpl semDao = new H2SemesterDAOImpl();
	sub.setSemester(semDao.getById(res.getInt("semester_id")));
	
	H2PersonDAOImpl persDao = new H2PersonDAOImpl();
	sub.setTeacher(persDao.getById(teacherId));
	
	out.add(sub);
      }
      
      return out;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get subjects for teacher [id = %d]", teacherId), ex);
      return null;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Subject> getBySemesterId(Integer semesterId) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM subject where semester_id = ?");
      stat.setInt(1, semesterId);
      ResultSet res = stat.executeQuery();
      
      List<Subject> out = new ArrayList<>();
      while (res.next()) {
	Subject sub = new Subject();
	
	sub.setId(res.getInt("subject_id"));
	sub.setSubjectName(res.getString("subject_name"));
	
	H2SemesterDAOImpl semDao = new H2SemesterDAOImpl();
	sub.setSemester(semDao.getById(semesterId));
	
	H2PersonDAOImpl persDao = new H2PersonDAOImpl();
	sub.setTeacher(persDao.getById(res.getInt("teacher_id")));
	
	out.add(sub);
      }
      
      return out;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get subjects for semester [id = %d]", semesterId), ex);
      return null;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }
}
