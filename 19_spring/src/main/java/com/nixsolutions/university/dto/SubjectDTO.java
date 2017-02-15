package com.nixsolutions.university.dto;

import java.util.ArrayList;
import java.util.List;

import com.nixsolutions.university.model.Subject;

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
  
  public SubjectDTO(Subject entity) {
    this.id = entity.getId();
    this.name = entity.getSubjectName();
    this.teacher = entity.getTeacher().getFirstName() + " " + entity.getTeacher().getLastName();
    this.semester = entity.getSemester().getSemesterName();
  }
  
  @Override
  public String toString() {
    return "SubjectDTO [id=" + id + ", name=" + name + ", teacher=" + teacher + ", semester=" + semester + "]";
  }
  
  public static List<SubjectDTO> convertFromSubject(List<Subject> in) {
    List<SubjectDTO> out = new ArrayList<>();
    in.forEach(p -> out.add(new SubjectDTO(p)));
    return out;
  }
}
