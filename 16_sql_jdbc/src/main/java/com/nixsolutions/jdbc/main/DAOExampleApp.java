package com.nixsolutions.jdbc.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import com.nixsolutions.jdbc.bean.Grade;
import com.nixsolutions.jdbc.bean.PersonType;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.GradeDAO;
import com.nixsolutions.jdbc.dao.PersonTypeDAO;

public class DAOExampleApp {

  private static final Logger LOG = LogManager.getLogger();
  
  public static void main (String[] args) throws Exception {
    DAOFactory fact = DAOFactory.getFactory(); 
    
//    LOG.info("--- Grade DAO demo");
//    runGradeDAO(fact.getGradeDAO());
//    LOG.info("--- Grade DAO demo finished");
//    
//    LOG.info("--- Person Type DAO demo");
//    runPersonTypeDAO(fact.getPersonTypeDAO());
//    LOG.info("--- Person Type DAO demo finished");
    
    runPersonDAO();
  }
  
  public static void runGradeDAO(GradeDAO dao) throws Exception {
   
    Grade[] grades = { new Grade("Fail", 1) ,
	new Grade("Pass", 2) ,
	new Grade("Average", 3) ,
	new Grade("Good", 4) ,
	new Grade("Very good", 5)
    };
    
    for (Grade g : grades) {
      LOG.info("Creating grade: " + g);
      dao.create(g);
    }
    
    Grade gr = dao.getById(3);
    LOG.info("Grade [ID = 3]: " + gr);
    
    dao.delete(2);
    
    for (Grade g : dao.getAll()) {
      LOG.info(g);
    }
  }

  public static void runPersonTypeDAO(PersonTypeDAO dao) throws Exception {
    PersonType[] types = { new PersonType("Administration", "Dean"),
	new PersonType("Teaching and grading", "Teacher"),
	new PersonType("Studying", "Student")
    };
    
    for (PersonType t : types) {
      LOG.info("Creating type: " + t);
      dao.create(t);
    }
    
    PersonType t = dao.getById(2);
    LOG.info("Type [ID = 2]: " + t);
    
    dao.delete(1);
    
    for (PersonType pt : dao.getAll()) {
      LOG.info(pt);
    }
  }
  
  public static void runPersonDAO() throws Exception {
    
  }
}
