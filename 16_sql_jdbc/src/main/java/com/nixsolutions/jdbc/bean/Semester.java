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
    return "Semester [id=" + id	+ ", semesterName=" + semesterName + ", startDate=" + startDate 
	+ ", endDate=" + endDate + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
    result = prime * result + ((semesterName == null) ? 0 : semesterName.hashCode());
    result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
    Semester other = (Semester) obj;
    if (endDate == null) {
      if (other.endDate != null)
	return false;
    } else if (!endDate.equals(other.endDate))
      return false;
    if (semesterName == null) {
      if (other.semesterName != null)
	return false;
    } else if (!semesterName.equals(other.semesterName))
      return false;
    if (startDate == null) {
      if (other.startDate != null)
	return false;
    } else if (!startDate.equals(other.startDate))
      return false;
    return true;
  }
}
