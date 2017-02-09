package com.nixsolutions.university.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Subject implements Serializable {

  private static final long serialVersionUID = 6810729967374225224L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "subject_id")
  private Integer id;
  
  @Column(name = "subject_name", unique = true)
  @Size(max = 100)
  @NotNull
  private String subjectName;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "teacher_id", referencedColumnName = "person_id")
  private Person teacher;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "semester_id", referencedColumnName = "semester_id")
  @NotNull
  private Semester semester;
  
  @OneToMany(mappedBy="subject", fetch = FetchType.LAZY, orphanRemoval = true)
  @OnDelete(action=OnDeleteAction.CASCADE)
  private List<Journal> records;

  public Subject() {
  }

  public Subject(String subjectName, Person teacher, Semester semester) {
    this.subjectName = subjectName;
    this.teacher = teacher;
    this.semester = semester;
  }

  public Subject(Integer id, String subjectName, Person teacher, Semester semester) {
    this(subjectName, teacher, semester);
    this.id = id;
  }
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getSubjectName() {
    return subjectName;
  }
  
  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }
  
  public Person getTeacher() {
    return teacher;
  }
  
  public void setTeacher(Person teacher) {
    this.teacher = teacher;
  }
  
  public Semester getSemester() {
    return semester;
  }
  
  public void setSemester(Semester semester) {
    this.semester = semester;
  }

  @Override
  public String toString() {
    return "Subject [id=" + id + ", subjectName=" + subjectName + ", teacher=" + teacher + ", semester=" + semester
        + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((semester == null) ? 0 : semester.hashCode());
    result = prime * result + ((subjectName == null) ? 0 : subjectName.hashCode());
    result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
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
    Subject other = (Subject) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (semester == null) {
      if (other.semester != null)
        return false;
    } else if (!semester.equals(other.semester))
      return false;
    if (subjectName == null) {
      if (other.subjectName != null)
        return false;
    } else if (!subjectName.equals(other.subjectName))
      return false;
    if (teacher == null) {
      if (other.teacher != null)
        return false;
    } else if (!teacher.equals(other.teacher))
      return false;
    return true;
  }  
}
