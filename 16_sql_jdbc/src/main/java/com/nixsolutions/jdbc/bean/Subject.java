package com.nixsolutions.jdbc.bean;

public class Subject extends AbstractBean {

  private static final long serialVersionUID = 6810729967374225224L;

  private String subjectName;
  private Person teacher;
  private Semester semester;
  
  public Subject() {}
  
  public Subject(String subjectName, Person teacher, Semester semester) {
    super();
    this.subjectName = subjectName;
    this.teacher = teacher;
    this.semester = semester;
  }
  
  public Subject(Integer id, String subjectName, Person teacher, Semester semester) {
    this(subjectName, teacher, semester);
    this.id = id;
  }

  public String getSubjectName() {
    return subjectName;
  }
  
  public void setSubjectName(String subjectName) {
    if (subjectName != null) {
      this.subjectName = subjectName;
    } else {
      throw new RuntimeException("Subject name should not be null");
    }
  }
  
  public Person getTeacher() {
    return teacher;
  }
  
  public void setTeacher(Person teacher) {
    if (teacher != null) {
      this.teacher = teacher;
    } else {
      throw new RuntimeException("Teacher should not be null");
    }
  }
  
  public Semester getSemester() {
    return semester;
  }
  
  public void setSemester(Semester semester) {
    this.semester = semester;
  }

  @Override
  public String toString() {
    return "Subject [id=" + id	+ ", subjectName=" + subjectName 
	+ ", teacher=" + teacher + ", semester=" + semester + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((semester == null) ? 0 : semester.hashCode());
    result = prime * result + ((subjectName == null) ? 0 : subjectName.hashCode());
    result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
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
    Subject other = (Subject) obj;
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
