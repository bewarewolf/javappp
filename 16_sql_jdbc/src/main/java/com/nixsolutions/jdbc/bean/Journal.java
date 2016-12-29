package com.nixsolutions.jdbc.bean;

import java.time.LocalDate;

public class Journal {

  private Integer recordId;
  private Person person;
  private Subject subject;
  private Grade grade;
  private LocalDate gradeDate;
  
  public Integer getRecordId() {
    return recordId;
  }
  
  public void setRecordId(Integer recordId) {
    this.recordId = recordId;
  }
  
  public Person getPerson() {
    return person;
  }
  
  public void setPerson(Person person) {
    this.person = person;
  }
  
  public Subject getSubject() {
    return subject;
  }
  
  public void setSubject(Subject subject) {
    this.subject = subject;
  }
  
  public Grade getGrade() {
    return grade;
  }
  
  public void setGrade(Grade grade) {
    this.grade = grade;
  }
  
  public LocalDate getGradeDate() {
    return gradeDate;
  }
  
  public void setGradeDate(LocalDate gradeDate) {
    this.gradeDate = gradeDate;
  }  
}
