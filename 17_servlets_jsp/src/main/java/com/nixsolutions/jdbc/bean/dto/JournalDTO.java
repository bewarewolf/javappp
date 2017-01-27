package com.nixsolutions.jdbc.bean.dto;

import com.nixsolutions.jdbc.bean.Journal;
import com.nixsolutions.jdbc.dao.DAOFactory;

public class JournalDTO {

  private Integer id;
  private String student;
  private String subject;
  private String grade;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStudent() {
    return student;
  }

  public void setStudent(String student) {
    this.student = student;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  @Override
  public String toString() {
    return "JournalDTO [id=" + id + ", student=" + student + ", subject=" + subject + ", grade=" + grade + "]";
  }

  public JournalDTO(Integer id, String student, String subject, String grade) {
    super();
    this.id = id;
    this.student = student;
    this.subject = subject;
    this.grade = grade;
  }

  public JournalDTO(Journal rec) {
    this.id = rec.getId();

    try {
      this.student = new PersonDTO(DAOFactory.getFactory().getPersonDAO().getById(rec.getPersonId())).getName();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
    try {
      this.subject = DAOFactory.getFactory().getSubjectDAO().getById(rec.getSubjectId()).getSubjectName();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
    try {
      this.grade = DAOFactory.getFactory().getGradeDAO().getById(rec.getGradeId()).getDescription();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
