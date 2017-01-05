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

import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.dao.PersonDAO;

public class H2PersonDAOImpl implements PersonDAO {

  private static final Logger LOG = LogManager.getLogger();

  @Override
  public int create(Person bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("INSERT INTO person (first_name, middle_name, last_name,"
	  + "birthday, date_start, person_type_id, person_status_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
      stat.setString(1, bean.getFirstName());
      stat.setString(2, bean.getMiddleName());
      stat.setString(3, bean.getLastName());
      stat.setDate(4, java.sql.Date.valueOf(bean.getBirthday()));
      stat.setDate(5, java.sql.Date.valueOf(bean.getStartDate()));
      stat.setInt(6, bean.getPersonTypeId());
      stat.setInt(7, bean.getPersonStatusId());
      stat.executeUpdate();

      ResultSet res = stat.getGeneratedKeys();

      if (res.next()) {
	return res.getInt(1);
      }

      return -1;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add person [%s]", bean.toString()), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public boolean update(Person bean) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement(
	  "UPDATE person SET " + "first_name = ?, " + "middle_name = ?, " + "last_name = ?, " + "birthday = ?, "
	      + "date_start = ?, " + "person_type_id = ?, " + "person_status_id = ? " + "WHERE person_id = ?");

      stat.setString(1, bean.getFirstName());
      stat.setString(2, bean.getMiddleName());
      stat.setString(3, bean.getLastName());
      stat.setDate(4, java.sql.Date.valueOf(bean.getBirthday()));
      stat.setDate(5, java.sql.Date.valueOf(bean.getStartDate()));
      stat.setInt(6, bean.getPersonTypeId());
      stat.setInt(7, bean.getPersonStatusId());
      stat.setInt(8, bean.getId());

      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't update person [%s]", bean.toString()), ex);
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
      stat = conn.prepareStatement("DELETE FROM person where person_id = ?");
      stat.setInt(1, id);

      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error("Can't delete person", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  protected Person processRecord(ResultSet res) throws SQLException {
    Person pers = new Person();

    pers.setId(res.getInt("person_id"));
    pers.setFirstName(res.getString("first_name"));
    pers.setMiddleName(res.getString("middle_name"));
    pers.setLastName(res.getString("last_name"));
    pers.setBirthday(res.getDate("birthday").toLocalDate());
    pers.setStartDate(res.getDate("date_start").toLocalDate());
    pers.setPersonTypeId(res.getInt("person_type_id"));
    pers.setPersonStatusId(res.getInt("person_status_id"));

    return pers;
  }

  @Override
  public Person getById(Integer id) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM person where person_id = ?");
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();

      if (res.next()) {
	return processRecord(res);
      }

      return null;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get person [id = %d]", id), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Person> getAll() throws SQLException {
    Connection conn = null;
    Statement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.createStatement();
      ResultSet res = stat.executeQuery("SELECT * FROM person");

      List<Person> out = new ArrayList<>();
      while (res.next()) {
	out.add(processRecord(res));
      }

      return out;
    } catch (SQLException ex) {
      LOG.error("Can't get list of persons", ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public Person getByName(String firstName, String lastName) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM person where first_name = ? and last_name = ?");
      stat.setString(1, firstName);
      stat.setString(2, lastName);
      ResultSet res = stat.executeQuery();

      if (res.next()) {	
	return processRecord(res);
      }

      return null;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get person [name = %s %s]", firstName, lastName), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Person> getByType(Integer typeId) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM person where person_type_id = ?");
      stat.setInt(1, typeId);
      ResultSet res = stat.executeQuery();

      List<Person> out = new ArrayList<>();
      while (res.next()) {	
	out.add(processRecord(res));
      }

      return out;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get person [type id = %d]", typeId), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Person> getByStatus(Integer statusId) throws SQLException {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM person where person_status_id = ?");
      stat.setInt(1, statusId);
      ResultSet res = stat.executeQuery();

      List<Person> out = new ArrayList<>();
      while (res.next()) {
	out.add(processRecord(res));
      }

      return out;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get person [status id = %d]", statusId), ex);
      throw ex;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }
}
