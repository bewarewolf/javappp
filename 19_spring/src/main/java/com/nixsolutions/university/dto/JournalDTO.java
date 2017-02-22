package com.nixsolutions.university.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.nixsolutions.university.model.Journal;

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
    this.student = rec.getPerson().getFirstName() + " " + rec.getPerson().getLastName();
    this.subject = rec.getSubject().getSubjectName();
    this.grade = rec.getGrade().getDescription();
  }
  
  public static List<JournalDTO> convertFromJournal(List<Journal> in) {
    return in.stream().map(j -> new JournalDTO(j)).collect(Collectors.toList());
  }
}
