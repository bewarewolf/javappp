package com.nixsolutions.jdbc.bean;

import java.time.LocalDate;

public class Journal extends AbstractBean {

  private static final long serialVersionUID = -2433296202714953695L;
  
  private Person person;
  private Subject subject;
  private Grade grade;
  private LocalDate gradeDate;
    
  public Journal() {}
  
  public Journal(Person person, Subject subject, Grade grade, LocalDate gradeDate) {
    super();
    this.person = person;
    this.subject = subject;
    this.grade = grade;
    this.gradeDate = gradeDate;
  }
  
  public Journal(Integer id, Person person, Subject subject, Grade grade, LocalDate gradeDate) {
    this(person, subject, grade, gradeDate);
    this.id = id;
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
 
  @Override
  public String toString() {
    return "Journal [id=" + id + ", person=" + person + ", subject=" + subject 
	+ ", grade=" + grade + ", gradeDate=" + gradeDate + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((grade == null) ? 0 : grade.hashCode());
    result = prime * result + ((gradeDate == null) ? 0 : gradeDate.hashCode());
    result = prime * result + ((person == null) ? 0 : person.hashCode());
    result = prime * result + ((subject == null) ? 0 : subject.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    Journal other = (Journal) obj;
    if (grade == null) {
      if (other.grade != null)
	return false;
    } else if (!grade.equals(other.grade))
      return false;
    if (gradeDate == null) {
      if (other.gradeDate != null)
	return false;
    } else if (!gradeDate.equals(other.gradeDate))
      return false;
    if (person == null) {
      if (other.person != null)
	return false;
    } else if (!person.equals(other.person))
      return false;
    if (subject == null) {
      if (other.subject != null)
	return false;
    } else if (!subject.equals(other.subject))
      return false;
    return true;
  }
}
