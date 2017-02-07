package com.nixsolutions.jdbc.bean;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.nixsolutions.jdbc.util.LocalDateAttributeConverter;

@Entity
public class Journal implements Serializable {

  private static final long serialVersionUID = -2433296202714953695L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "record_id")
  private Integer id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id", referencedColumnName = "person_id")
  @NotNull
  private Person person;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
  @NotNull
  private Subject subject;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "grade_id", referencedColumnName = "grade_id")
  @NotNull
  private Grade grade;
  
  @Column(name = "grade_date", columnDefinition = "timestamp")
  @Convert(converter = LocalDateAttributeConverter.class)
  @NotNull
  private LocalDate gradeDate;

  public Integer getId() {
    return id;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public Grade getGrade() {
    return grade;
  }

  public void setGrade(Grade grade) {
    this.grade = grade;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public LocalDate getGradeDate() {
    return gradeDate;
  }

  public void setGradeDate(LocalDate gradeDate) {
    this.gradeDate = gradeDate;
  }

  public Journal() {
  }

  public Journal(Person person, Subject subject, Grade grade) {
    this.person = person;
    this.subject = subject;
    this.grade = grade;
  }

  public Journal(Integer id, Person person, Subject subject, Grade grade) {
    this(person, subject, grade);
    this.id = id;
  }

  
  public Subject getSubject() {
    return subject;
  }

  
  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  public Journal(Person person, Subject subject, Grade grade, LocalDate gradeDate) {
    this(person, subject, grade);
    this.gradeDate = gradeDate;
  }

  public Journal(Integer id, Person person, Subject subject, Grade grade, LocalDate gradeDate) {
    this(person, subject, grade, gradeDate);
    this.id = id;
  }

  @Override
  public String toString() {
    return "Journal [id=" + id + ", person=" + person + ", subject=" + subject + ", grade=" + grade + ", gradeDate="
        + gradeDate + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((grade == null) ? 0 : grade.hashCode());
    result = prime * result + ((gradeDate == null) ? 0 : gradeDate.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((person == null) ? 0 : person.hashCode());
    result = prime * result + ((subject == null) ? 0 : subject.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
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
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
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
