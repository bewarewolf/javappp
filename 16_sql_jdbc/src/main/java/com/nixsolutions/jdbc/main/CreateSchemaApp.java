package com.nixsolutions.jdbc.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateSchemaApp {

  private static final Logger LOG = LogManager.getLogger();

  public static void main(String[] args) throws ClassNotFoundException {

    LOG.info("Starting CREATE SCHEMA application");

    try {
      Class.forName("org.h2.Driver");
    } catch (ClassNotFoundException ex) {
      LOG.error("Can't load driver", ex);
    }

    Connection conn = null;
    Statement st = null;

    try {
      FileInputStream fis = new FileInputStream("jdbc.properties");
      Properties prop = new Properties();
      prop.load(fis);

      String strUrl = prop.getProperty("db.url");
      String strDbName = prop.getProperty("db.name");
      String strUsername = prop.getProperty("db.username");
      String strPassword = prop.getProperty("db.password");

      conn = DriverManager.getConnection(strUrl + "~/" + strDbName, strUsername, strPassword);
      st = conn.createStatement();

      StringBuilder sbCreateGrade = new StringBuilder("CREATE TABLE grade (")
	  .append(" grade_id INT IDENTITY,")
 	  .append(" description VARCHAR(100) NOT NULL,")
      	  .append(" value INT UNIQUE NOT NULL")
      	  .append(");");
      LOG.info(sbCreateGrade.toString());
      st.addBatch(sbCreateGrade.toString());

      StringBuilder sbCreatePerson = new StringBuilder("CREATE TABLE person (")
	  .append(" person_id INT IDENTITY,")
	  .append(" first_name VARCHAR(100) NOT NULL,")
	  .append(" middle_name VARCHAR(100),")
	  .append(" last_name VARCHAR(100) NOT NULL,")
	  .append(" birthday DATE NOT NULL,")
	  .append(" date_start DATE NOT NULL,")
	  .append(" person_type_id INT NOT NULL,")
	  .append(" person_status_id INT NOT NULL")
	  .append(");");
      LOG.info(sbCreatePerson.toString());
      st.addBatch(sbCreatePerson.toString());

      StringBuilder sbCreateSubject = new StringBuilder("CREATE TABLE subject (")
	  .append(" subject_id INT IDENTITY,")
	  .append(" subject_name VARCHAR(100) NOT NULL UNIQUE,")
	  .append(" teacher_id INT,")
	  .append(" semester_id INT NOT NULL")
	  .append(");");
      LOG.info(sbCreateSubject.toString());
      st.addBatch(sbCreateSubject.toString());

      StringBuilder sbCreateSemester = new StringBuilder("CREATE TABLE semester (")
	  .append(" semester_id INT IDENTITY,")
	  .append(" semester_name VARCHAR(20) NOT NULL UNIQUE,")
	  .append(" semester_date_start DATE NOT NULL,")
	  .append(" semester_date_end DATE NOT NULL")
	  .append(");");
      LOG.info(sbCreateSemester.toString());
      st.addBatch(sbCreateSemester.toString());

      StringBuilder sbCreateJournal = new StringBuilder("CREATE TABLE journal (")
          .append(" record_id INT IDENTITY,")
          .append(" person_id INT NOT NULL,")
          .append(" subject_id INT NOT NULL,")
          .append(" grade_id INT NOT NULL,")
          .append(" grade_date TIMESTAMP NOT NULL DEFAULT CURRENT_DATE(),")
          .append(" FOREIGN KEY (grade_id) REFERENCES grade (grade_id) ON DELETE CASCADE,")
          .append(" FOREIGN KEY (person_id) REFERENCES person (person_id) ON DELETE CASCADE,")
          .append(" FOREIGN KEY (subject_id) REFERENCES subject (subject_id) ON DELETE CASCADE")
          .append(");");
      LOG.info(sbCreateJournal.toString());
      st.addBatch(sbCreateJournal.toString());

      StringBuilder sbCreatePhoneNumber = new StringBuilder("CREATE TABLE phone_number (")
          .append(" phone_number_id INT IDENTITY,")
          .append(" phone_number VARCHAR(20) NOT NULL UNIQUE,")
          .append(" person_id INT NOT NULL,")
          .append(" FOREIGN KEY (person_id) REFERENCES person (person_id) ON DELETE CASCADE")
          .append(");");
      LOG.info(sbCreatePhoneNumber.toString());
      st.addBatch(sbCreatePhoneNumber.toString());

      StringBuilder sbCreatePersonType = new StringBuilder("CREATE TABLE person_type (")
          .append(" person_type_id INT IDENTITY,")
          .append(" description VARCHAR(100),")
          .append(" value VARCHAR(20) NOT NULL UNIQUE")
          .append(");");
      LOG.info(sbCreatePersonType.toString());
      st.addBatch(sbCreatePersonType.toString());

      StringBuilder sbCreatePersonStatus = new StringBuilder("CREATE TABLE person_status (")
          .append(" person_status_id INT IDENTITY,")
          .append(" description VARCHAR(100),")
          .append(" value VARCHAR(20) NOT NULL UNIQUE")
          .append(");");
      LOG.info(sbCreatePersonStatus.toString());
      st.addBatch(sbCreatePersonStatus.toString());

      StringBuilder sbAlterPerson1 = new StringBuilder("ALTER TABLE person")
	  .append(" ADD FOREIGN KEY (person_type_id) REFERENCES person_type (person_type_id)");
      LOG.info(sbAlterPerson1.toString());
      st.addBatch(sbAlterPerson1.toString());

      StringBuilder sbAlterPerson2 = new StringBuilder("ALTER TABLE person")
	  .append(" ADD FOREIGN KEY (person_status_id) REFERENCES person_status (person_status_id)");
      LOG.info(sbAlterPerson2.toString());
      st.addBatch(sbAlterPerson2.toString());

      StringBuilder sbAlterSubject1 = new StringBuilder("ALTER TABLE subject")
	  .append(" ADD FOREIGN KEY (teacher_id) REFERENCES person (person_id) ON DELETE CASCADE;");
      LOG.info(sbAlterSubject1.toString());
      st.addBatch(sbAlterSubject1.toString());

      StringBuilder sbAlterSubject2 = new StringBuilder("ALTER TABLE subject")
	  .append(" ADD FOREIGN KEY (semester_id) REFERENCES semester (semester_id) ON DELETE CASCADE;");
      LOG.info(sbAlterSubject2.toString());
      st.addBatch(sbAlterSubject2.toString());

      st.executeBatch();

      LOG.info("Schema has been created");
    } catch (SQLException | IOException ex) {
      LOG.error("Can't create schema", ex);
    } finally {
      try {
	if (st != null) {
	  st.close();
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
