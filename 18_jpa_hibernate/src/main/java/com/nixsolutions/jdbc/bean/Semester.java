package com.nixsolutions.jdbc.bean;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.nixsolutions.jdbc.util.LocalDateAttributeConverter;

@Entity
public class Semester implements Serializable {

  private static final long serialVersionUID = -4230804482238449898L;

  @Id
  @Column(name = "semester_id")
  private Integer id;
  
  @Column(name = "semester_name", length = 20, nullable = false, unique = true)
  private String semesterName;
  
  @Column(name = "semester_date_start", columnDefinition = "date", nullable = false)
  @Convert(converter = LocalDateAttributeConverter.class)
  private LocalDate startDate;
  
  @Column(name = "semester_date_end", columnDefinition = "date", nullable = false)
  @Convert(converter = LocalDateAttributeConverter.class)
  private LocalDate endDate;

  public Semester() {
  }

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

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSemesterName() {
    return semesterName;
  }

  public void setSemesterName(String semesterName) {
    this.semesterName = semesterName;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  @Override
  public String toString() {
    return "Semester [id=" + id + ", semesterName=" + semesterName + ", startDate=" + startDate + ", endDate=" + endDate
        + "]";
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
