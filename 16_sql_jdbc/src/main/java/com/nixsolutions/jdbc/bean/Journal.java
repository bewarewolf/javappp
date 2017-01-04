package com.nixsolutions.jdbc.bean;

import java.time.LocalDate;

public class Journal extends AbstractBean {

  private static final long serialVersionUID = -2433296202714953695L;
  
  private Person person;
  private Subject subject;
  private Grade grade;
  private LocalDate gradeDate;
    
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
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ID: ");
    sb.append(id);
    sb.append("; Person: ");
    sb.append(person.getFullName());
    sb.append("; Grade: ");
    sb.append(grade.getDescription());
    return sb.toString();
  }
}
