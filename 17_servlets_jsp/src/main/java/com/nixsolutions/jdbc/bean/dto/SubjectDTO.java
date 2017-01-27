package com.nixsolutions.jdbc.bean.dto;

public class SubjectDTO {

  private Integer id;
  private String name;
  private String teacher;
  private String semester;
  
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getTeacher() {
    return teacher;
  }
  public void setTeacher(String teacher) {
    this.teacher = teacher;
  }
  public String getSemester() {
    return semester;
  }
  public void setSemester(String semester) {
    this.semester = semester;
  }
  
  public SubjectDTO(Integer id, String name, String teacher, String semester) {
    super();
    this.id = id;
    this.name = name;
    this.teacher = teacher;
    this.semester = semester;
  }
  
  @Override
  public String toString() {
    return "SubjectDTO [id=" + id + ", name=" + name + ", teacher=" + teacher + ", semester=" + semester + "]";
  }
}
