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
import com.nixsolutions.jdbc.bean.PersonStatus;
import com.nixsolutions.jdbc.bean.PersonType;
import com.nixsolutions.jdbc.bean.PhoneNumber;
import com.nixsolutions.jdbc.bean.Subject;
import com.nixsolutions.jdbc.dao.PersonDAO;

public class H2PersonDAOImpl implements PersonDAO {

  private static final Logger LOG = LogManager.getLogger();

  @Override
  public int create(Person bean) {
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
      stat.setInt(6, bean.getPersonType().getId());
      stat.setInt(7, bean.getPersonStatus().getId());
      stat.executeUpdate();
      
      ResultSet res = stat.getGeneratedKeys(); 
      int personId = -1;
      while (res.next()) {
        return res.getInt(1);
      }
      
      if (personId != -1 && bean.getPhoneNumbers() != null) {
        for (PhoneNumber pn : bean.getPhoneNumbers()) {
          pn.setPersonId(personId);
          addPhoneNumber(pn);
        }
      }
      
      return personId;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add person [%s]", bean.toString()), ex);
      return -1;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public boolean update(Person bean) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("UPDATE person SET "
          + "first_name = ?, "
          + "middle_name = ?, "
          + "last_name = ?, "
          + "birthday = ?, "
          + "date_start = ?, "
          + "person_type_id = ?, "
          + "person_status_id = ? "
          + "WHERE person_id = ?");
      
      stat.setString(1, bean.getFirstName());
      stat.setString(2, bean.getMiddleName());
      stat.setString(3, bean.getLastName());
      stat.setDate(4, java.sql.Date.valueOf(bean.getBirthday()));
      stat.setDate(5, java.sql.Date.valueOf(bean.getStartDate()));
      stat.setInt(6, bean.getPersonType().getId());
      stat.setInt(7, bean.getPersonStatus().getId());
      stat.setInt(8, bean.getId());
            
      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't add person [%s]", bean.toString()), ex);
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
      stat = conn.prepareStatement("DELETE FROM person where person_id = ?");
      stat.setInt(1, id);

      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error("Can't delete person", ex);
      return false;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public Person getById(Integer id) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM person where person_id = ?");
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();

      Person pers = new Person();
      while (res.next()) {
	pers.setId(res.getInt("subject_id"));
	pers.setFirstName(res.getString("first_name"));
	pers.setMiddleName(res.getString("middle_name"));
	pers.setLastName(res.getString("last_name"));
	pers.setBirthday(res.getDate("birthday").toLocalDate());
	pers.setStartDate(res.getDate("date_start").toLocalDate());

	H2PersonTypeDAOImpl typeDao = new H2PersonTypeDAOImpl();
	pers.setPersonType(typeDao.getById(res.getInt("person_type_id")));

	H2PersonStatusDAOImpl statusDao = new H2PersonStatusDAOImpl();
	pers.setPersonStatus(statusDao.getById(res.getInt("person_status_id")));
      }

      return pers;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get person [id = %d]", id), ex);
      return null;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public List<Person> getAll() {
    Connection conn = null;
    Statement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.createStatement();
      ResultSet res = stat.executeQuery("SELECT * FROM person");

      List<Person> out = new ArrayList<>();
      while (res.next()) {
	Person pers = new Person();
	
	pers.setId(res.getInt("subject_id"));
	pers.setFirstName(res.getString("first_name"));
	pers.setMiddleName(res.getString("middle_name"));
	pers.setLastName(res.getString("last_name"));
	pers.setBirthday(res.getDate("birthday").toLocalDate());
	pers.setStartDate(res.getDate("date_start").toLocalDate());

	H2PersonTypeDAOImpl typeDao = new H2PersonTypeDAOImpl();
	pers.setPersonType(typeDao.getById(res.getInt("person_type_id")));

	H2PersonStatusDAOImpl statusDao = new H2PersonStatusDAOImpl();
	pers.setPersonStatus(statusDao.getById(res.getInt("person_status_id")));

	out.add(pers);
      }

      return out;
    } catch (SQLException ex) {
      LOG.error("Can't get list of persons", ex);
      return null;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public Person getByName(String firstName, String lastName) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("SELECT * FROM person where first_name = '?' and last_name = '?'");
      stat.setString(1, firstName);
      stat.setString(1, lastName);
      ResultSet res = stat.executeQuery();

      Person pers = new Person();
      while (res.next()) {
	pers.setId(res.getInt("subject_id"));
	pers.setFirstName(res.getString("first_name"));
	pers.setMiddleName(res.getString("middle_name"));
	pers.setLastName(res.getString("last_name"));
	pers.setBirthday(res.getDate("birthday").toLocalDate());
	pers.setStartDate(res.getDate("date_start").toLocalDate());

	H2PersonTypeDAOImpl typeDao = new H2PersonTypeDAOImpl();
	pers.setPersonType(typeDao.getById(res.getInt("person_type_id")));

	H2PersonStatusDAOImpl statusDao = new H2PersonStatusDAOImpl();
	pers.setPersonStatus(statusDao.getById(res.getInt("person_status_id")));
      }

      return pers;
    } catch (SQLException ex) {
      LOG.error(String.format("Can't get person [name = %s %s]", firstName, lastName), ex);
      return null;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public boolean setPersonType(Person p, PersonType t) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("UPDATE person SET person_type_id = ? where person_id = ?");
      stat.setInt(1, t.getId());
      stat.setInt(2, p.getId());

      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error("Can't delete person", ex);
      return false;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public boolean setPersonStatus(Person p, PersonStatus t) {
    Connection conn = null;
    PreparedStatement stat = null;
    try {
      conn = H2ConnectionManager.getConnection();
      stat = conn.prepareStatement("UPDATE person SET person_status_id = ? where person_id = ?");
      stat.setInt(1, t.getId());
      stat.setInt(2, p.getId());

      return stat.executeUpdate() != 0;
    } catch (SQLException ex) {
      LOG.error("Can't delete person", ex);
      return false;
    } finally {
      DbUtils.closeQuietly(conn);
      DbUtils.closeQuietly(stat);
    }
  }

  @Override
  public int addPhoneNumber(PhoneNumber pn) {
    H2PhoneNumberDAOImpl pnDao = new H2PhoneNumberDAOImpl();
    return pnDao.create(pn);
  }

  @Override
  public boolean removePhoneNumber(PhoneNumber pn) {
    H2PhoneNumberDAOImpl pnDao = new H2PhoneNumberDAOImpl();
    return pnDao.delete(pn.getId());
  }

  @Override
  public int addPhoneNumber(Integer personId, String number) {
    PhoneNumber pn = new PhoneNumber();
    pn.setPersonId(personId);
    pn.setPhoneNumber(number);
    return addPhoneNumber(pn);
  }

}
