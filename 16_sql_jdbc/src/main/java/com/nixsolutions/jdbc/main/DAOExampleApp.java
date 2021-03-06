package com.nixsolutions.jdbc.main;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.jdbc.bean.Grade;
import com.nixsolutions.jdbc.bean.Journal;
import com.nixsolutions.jdbc.bean.Person;
import com.nixsolutions.jdbc.bean.PersonStatus;
import com.nixsolutions.jdbc.bean.PersonType;
import com.nixsolutions.jdbc.bean.PhoneNumber;
import com.nixsolutions.jdbc.bean.Semester;
import com.nixsolutions.jdbc.bean.Subject;
import com.nixsolutions.jdbc.dao.DAOFactory;
import com.nixsolutions.jdbc.dao.GradeDAO;
import com.nixsolutions.jdbc.dao.JournalDAO;
import com.nixsolutions.jdbc.dao.PersonDAO;
import com.nixsolutions.jdbc.dao.PersonStatusDAO;
import com.nixsolutions.jdbc.dao.PersonTypeDAO;
import com.nixsolutions.jdbc.dao.PhoneNumberDAO;
import com.nixsolutions.jdbc.dao.SemesterDAO;
import com.nixsolutions.jdbc.dao.SubjectDAO;

public class DAOExampleApp {

  private static final Logger LOG = LogManager.getLogger();

  public static void main(String[] args) throws Exception {
    DAOFactory fact = DAOFactory.getFactory();

    LOG.info("--- Grade DAO demo");
    runGradeDAO(fact.getGradeDAO());
    LOG.info("--- Grade DAO demo finished");

    LOG.info("--- Person Type DAO demo");
    runPersonTypeDAO(fact.getPersonTypeDAO());
    LOG.info("--- Person Type DAO demo finished");

    LOG.info("--- Person Status DAO demo");
    runPersonStatusDAO(fact.getPersonStatusDAO());
    LOG.info("--- Person Status DAO demo finished");

    LOG.info("--- Person DAO demo");
    runPersonDAO(fact.getPersonDAO());
    LOG.info("--- Person DAO demo finished");

    LOG.info("--- Phone Number DAO demo");
    runPhoneNumberDAO(fact.getPhoneNumberDAO());
    LOG.info("--- Phone Number DAO demo finished");

    LOG.info("--- Semester DAO demo");
    runSemesterDAO(fact.getSemesterDAO());
    LOG.info("--- Semester DAO demo finished");

    LOG.info("--- Subject DAO demo");
    runSubjectDAO(fact.getSubjectDAO());
    LOG.info("--- Subject DAO demo finished");

    LOG.info("--- Journal DAO demo");
    runJournalDAO(fact.getJournalDAO());
    LOG.info("--- Journal DAO demo finished");
  }

  public static void runGradeDAO(GradeDAO dao) throws Exception {
    Grade g1 = new Grade("new", 34);
    LOG.info("Creating grade: " + g1);
    Integer gId1 = dao.create(g1);
    
    Grade g2 = new Grade("new1", 35);
    LOG.info("Creating grade: " + g2);
    Integer gId2 = dao.create(g2);
    
    Grade g3 = new Grade("new1", 36);
    LOG.info("Creating grade: " + g3);
    Integer gId3 = dao.create(g3);

    Grade g10 = dao.getById(gId1);
    LOG.info(g10);

    dao.delete(gId1);

    for (Grade gr : dao.getAll()) {
      LOG.info(gr);
    }
    
    dao.delete(gId2);
    dao.delete(gId3);
  }

  public static void runPersonTypeDAO(PersonTypeDAO dao) throws Exception {
    PersonType pt = new PersonType("descr", "val");

    LOG.info("Creating type: " + pt);
    Integer ptId = dao.create(pt);

    PersonType pt1 = dao.getById(ptId);
    LOG.info(pt1);

    pt1.setDescription("Updated");
    dao.update(pt1);
    LOG.info("Updated:" + dao.getById(pt1.getId()));
    
    dao.delete(ptId);

    for (PersonType pt2 : dao.getAll()) {
      LOG.info(pt2);
    }
  }

  public static void runPersonStatusDAO(PersonStatusDAO dao) throws Exception {
    PersonStatus ps = new PersonStatus("desc", "val");

    LOG.info("Creating status" + ps);
    Integer psId = dao.create(ps);

    PersonStatus ps1 = dao.getById(psId);
    LOG.info(ps1);

    ps1.setDescription("Updated");
    dao.update(ps1);
    LOG.info("Updated:" + dao.getById(ps1.getId()));
    
    dao.delete(psId);

    for (PersonStatus ps2 : dao.getAll()) {
      LOG.info(ps2);
    }
  }

  public static void runPersonDAO(PersonDAO dao) throws Exception {
    PersonType pt = new PersonType("type", "forPerson");
    Integer ptId = DAOFactory.getFactory().getPersonTypeDAO().create(pt);
    
    PersonStatus ps = new PersonStatus("status", "forPerson");
    Integer psId = DAOFactory.getFactory().getPersonStatusDAO().create(ps);
    
    Person p = new Person("New", "Middle", "Name", LocalDate.now(), LocalDate.now(), ptId, psId);

    LOG.info("Creating person" + p);
    Integer pId = dao.create(p);

    Person p1 = dao.getById(pId);
    LOG.info("Person by id:" + p1);

    Person p2 = dao.getByName("New", "Name");
    LOG.info("Person by name:" + p2);

    List<Person> p3 = dao.getByStatus(2);
    for (Person pers : p3) {
      LOG.info("Person by status:" + pers);
    }

    List<Person> p4 = dao.getByType(2);
    for (Person pers : p4) {
      LOG.info("Person by type:" + pers);
    }

    p2.setMiddleName("Updated");
    dao.update(p2);
    LOG.info("Updated:" + dao.getById(p2.getId()));
    
    dao.delete(pId);

    for (Person pers : dao.getAll()) {
      LOG.info(pers);
    }
    
    DAOFactory.getFactory().getPersonTypeDAO().delete(ptId);
    DAOFactory.getFactory().getPersonStatusDAO().delete(psId);
  }

  public static void runPhoneNumberDAO(PhoneNumberDAO dao) throws Exception {
    PersonType pt = new PersonType("type", "forPhone");
    Integer ptId = DAOFactory.getFactory().getPersonTypeDAO().create(pt);
    
    PersonStatus ps = new PersonStatus("status", "forPhone");
    Integer psId = DAOFactory.getFactory().getPersonStatusDAO().create(ps);
    
    Person p = new Person("Person", "For", "Phone", LocalDate.now(), LocalDate.now(), ptId, psId);
    Integer persId = DAOFactory.getFactory().getPersonDAO().create(p);

    PhoneNumber pn = new PhoneNumber(persId, "123-456-7890");

    LOG.info("Creating phone number:" + pn);
    Integer pnId = dao.create(pn);

    PhoneNumber pn1 = dao.getById(pnId);
    LOG.info("Phone number by id:" + pn1);
    
    pn1.setPhoneNumber("Updated");
    dao.update(pn1);
    LOG.info("Updated:" + dao.getById(pn1.getId()));
    
    dao.create(new PhoneNumber(persId, "45-54-12"));
    List<PhoneNumber> pn2 = dao.getByPersonId(pnId);
    for (PhoneNumber num : pn2) {
      LOG.info("Phone number by person id:" + num);
    }

    LOG.info("Deleting by phone id");
    dao.delete(pnId);
    for (PhoneNumber num : dao.getAll()) {
      LOG.info(num);
    }

    LOG.info("Deleting by person id");
    dao.deleteByPersonId(persId);
    for (PhoneNumber num : dao.getAll()) {
      LOG.info(num);
    }
    
    DAOFactory.getFactory().getPersonDAO().delete(persId);
    DAOFactory.getFactory().getPersonTypeDAO().delete(ptId);
    DAOFactory.getFactory().getPersonStatusDAO().delete(psId);    
  }

  public static void runSemesterDAO(SemesterDAO dao) throws Exception {
    Semester s = new Semester("new", LocalDate.now().plus(10, ChronoUnit.DAYS),
        LocalDate.now().plus(15, ChronoUnit.DAYS));

    LOG.info("Creating semester:" + s);
    Integer sId = dao.create(s);

    Semester s1 = dao.getById(sId);
    LOG.info("Semester by id:" + s1);

    Semester s2 = dao.getByName(s.getSemesterName());
    LOG.info("Semester by name:" + s2);

    s2.setSemesterName("Updated");
    dao.update(s2);
    LOG.info("Updated:" + dao.getById(s2.getId()));
    
    LOG.info("Current semester:" + dao.currentSemester());

    dao.delete(sId);

    for (Semester s3 : dao.getAll()) {
      LOG.info(s3);
    }
  }

  public static void runSubjectDAO(SubjectDAO dao) throws Exception {
    PersonType pt = new PersonType("type", "forSubject");
    Integer ptId = DAOFactory.getFactory().getPersonTypeDAO().create(pt);
    
    PersonStatus ps = new PersonStatus("status", "forSubject");
    Integer psId = DAOFactory.getFactory().getPersonStatusDAO().create(ps);
    
    Person pers = new Person("Person", "For", "Subject", LocalDate.now(), LocalDate.now(), ptId, psId);
    Integer persId = DAOFactory.getFactory().getPersonDAO().create(pers);
    
    Semester sem = new Semester("forSubject", LocalDate.now().plus(10, ChronoUnit.DAYS),
        LocalDate.now().plus(15, ChronoUnit.DAYS));
    Integer semId = DAOFactory.getFactory().getSemesterDAO().create(sem);    

    Subject s = new Subject("new", persId, semId);

    LOG.info("Creating subject:" + s);
    Integer subjId = dao.create(s);

    Subject s1 = dao.getById(subjId);
    LOG.info("Subject by id:" + s1);
    
    s1.setSubjectName("Updated");
    dao.update(s1);
    LOG.info("Updated:" + dao.getById(s1.getId()));

    List<Subject> s2 = dao.getBySemesterId(semId);
    for (Subject subj : s2) {
      LOG.info("Subject by semester:" + subj);
    }

    List<Subject> s3 = dao.getByTeacherId(persId);
    for (Subject subj : s3) {
      LOG.info("Subject by teacher:" + subj);
    }

    dao.delete(subjId);

    for (Subject subj : dao.getAll()) {
      LOG.info(subj);
    }
    
    DAOFactory.getFactory().getPersonDAO().delete(persId);
    DAOFactory.getFactory().getPersonTypeDAO().delete(ptId);
    DAOFactory.getFactory().getPersonStatusDAO().delete(psId);    
    DAOFactory.getFactory().getSemesterDAO().delete(semId);
  }

  public static void runJournalDAO(JournalDAO dao) throws Exception {
    PersonType pt = new PersonType("type", "forJournal");
    Integer ptId = DAOFactory.getFactory().getPersonTypeDAO().create(pt);
    
    PersonStatus ps = new PersonStatus("status", "forJournal");
    Integer psId = DAOFactory.getFactory().getPersonStatusDAO().create(ps);
    
    Person pers = new Person("Person", "For", "Journal", LocalDate.now(), LocalDate.now(), ptId, psId);
    Integer persId = DAOFactory.getFactory().getPersonDAO().create(pers);
    
    Semester sem = new Semester("forJournal", LocalDate.now().plus(10, ChronoUnit.DAYS),
        LocalDate.now().plus(15, ChronoUnit.DAYS));
    Integer semId = DAOFactory.getFactory().getSemesterDAO().create(sem);

    Subject s = new Subject("new", persId, semId);
    Integer subjId = DAOFactory.getFactory().getSubjectDAO().create(s);

    Grade g = new Grade("forJournal", 340);
    Integer grId = DAOFactory.getFactory().getGradeDAO().create(g);
    
    Grade g1 = new Grade("oneMoreForJournal", 350);
    Integer grId1 = DAOFactory.getFactory().getGradeDAO().create(g1);

    Journal j = new Journal(persId, subjId, grId);
    LOG.info("Creating journal:" + j);
    Integer jId = dao.create(j);

    Journal j1 = dao.getById(jId);
    LOG.info("Journal by id:" + j1);

    j1.setGradeId(grId1);
    dao.update(j1);
    LOG.info("Updated:" + dao.getById(j1.getId()));
    
    List<Journal> j2 = dao.getByPersonId(persId);
    for (Journal rec : j2) {
      LOG.info("Journal by person id:" + rec);
    }

    List<Journal> j3 = dao.getBySubjectId(subjId);
    for (Journal rec : j3) {
      LOG.info("Journal by subject id:" + rec);
    }

    dao.delete(jId);

    for (Journal rec : dao.getAll()) {
      LOG.info(rec);
    }
    
    DAOFactory.getFactory().getPersonDAO().delete(persId);
    DAOFactory.getFactory().getPersonTypeDAO().delete(ptId);
    DAOFactory.getFactory().getPersonStatusDAO().delete(psId);    
    DAOFactory.getFactory().getSemesterDAO().delete(semId);
    DAOFactory.getFactory().getGradeDAO().delete(grId);
    DAOFactory.getFactory().getGradeDAO().delete(grId1);
  }
}
