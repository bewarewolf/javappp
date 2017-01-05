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
    StringBuilder sb = new StringBuilder();
    sb.append("ID: ");
    sb.append(id);
    sb.append("; Name: ");
    sb.append(subjectName);
    sb.append("; Teacher: ");
    sb.append(teacher.getFullName());
    sb.append("; Semester: ");
    sb.append(semester.getSemesterName());
    return sb.toString();
  }
}
