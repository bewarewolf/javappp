package com.nixsolutions.jdbc.bean;

import java.time.LocalDate;

public class Journal extends AbstractBean {

  private static final long serialVersionUID = -2433296202714953695L;
  
  private Integer personId;
  private Integer subjectId;
  private Integer gradeId;
  private LocalDate gradeDate;
    
  public Integer getPersonId() {
    return personId;
  }

  public void setPersonId(Integer personId) {
    this.personId = personId;
  }

  public Integer getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(Integer subjectId) {
    this.subjectId = subjectId;
  }

  public Integer getGradeId() {
    return gradeId;
  }

  public void setGradeId(Integer gradeId) {
    this.gradeId = gradeId;
  }

  public LocalDate getGradeDate() {
    return gradeDate;
  }

  public void setGradeDate(LocalDate gradeDate) {
    this.gradeDate = gradeDate;
  }

  public Journal() {}

  public Journal(Integer personId, Integer subjectId, Integer gradeId) {    
    this.personId = personId;
    this.subjectId = subjectId;
    this.gradeId = gradeId;
  }
  
  public Journal(Integer personId, Integer subjectId, Integer gradeId, LocalDate gradeDate) {
    this(personId, subjectId, gradeId);
    this.gradeDate = gradeDate;
  }
  
  public Journal(Integer id, Integer personId, Integer subjectId, Integer gradeId, LocalDate gradeDate) {    
    this(personId, subjectId, gradeId, gradeDate);
    this.id = id;
  }

  @Override
  public String toString() {
    return "Journal [personId=" + personId + ", subjectId=" + subjectId + ", gradeId=" + gradeId + ", gradeDate="
	+ gradeDate + ", id=" + id + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((gradeDate == null) ? 0 : gradeDate.hashCode());
    result = prime * result + ((gradeId == null) ? 0 : gradeId.hashCode());
    result = prime * result + ((personId == null) ? 0 : personId.hashCode());
    result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
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
    Journal other = (Journal) obj;
    if (gradeDate == null) {
      if (other.gradeDate != null)
	return false;
    } else if (!gradeDate.equals(other.gradeDate))
      return false;
    if (gradeId == null) {
      if (other.gradeId != null)
	return false;
    } else if (!gradeId.equals(other.gradeId))
      return false;
    if (personId == null) {
      if (other.personId != null)
	return false;
    } else if (!personId.equals(other.personId))
      return false;
    if (subjectId == null) {
      if (other.subjectId != null)
	return false;
    } else if (!subjectId.equals(other.subjectId))
      return false;
    return true;
  }
}
