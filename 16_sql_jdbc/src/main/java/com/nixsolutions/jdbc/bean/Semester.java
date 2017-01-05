package com.nixsolutions.jdbc.bean;

import java.time.LocalDate;

public class Semester extends AbstractBean {
  
  private static final long serialVersionUID = -4230804482238449898L;
  
  private String semesterName;
  private LocalDate startDate;
  private LocalDate endDate;
  
  public Semester() {}
  
  public Semester(String semesterName, LocalDate startDate, LocalDate endDate) {
    super();
    this.semesterName = semesterName;
    this.startDate = startDate;
    this.endDate = endDate;
  }
  
  public Semester(Integer id, String semesterName, LocalDate startDate, LocalDate endDate) {
    this(semesterName, startDate, endDate);
    this.id = id;
  }

  public String getSemesterName() {
    return semesterName;
  }
  
  public void setSemesterName(String semesterName) {
    if (semesterName != null) {
      this.semesterName = semesterName;
    } else {
      throw new RuntimeException("Semester name should not be null");
    }
  }
  
  public LocalDate getStartDate() {
    return startDate;
  }
  
  public void setStartDate(LocalDate startDate) {
    if (startDate != null) {
      this.startDate = startDate;
    } else {
      throw new RuntimeException("Start date should not be null");
    }
  }
  
  public LocalDate getEndDate() {
    return endDate;
  }
  
  public void setEndDate(LocalDate endDate) {
    if (endDate != null) {
      this.endDate = endDate;
    } else {
      throw new RuntimeException("End date should not be null");
    }
  }  
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ID: ");
    sb.append(id);
    sb.append("; Name: ");
    sb.append(semesterName);
    sb.append("; Start date: ");
    sb.append(startDate);
    sb.append("; End date: ");
    sb.append(endDate);
    return sb.toString();
  }
}
