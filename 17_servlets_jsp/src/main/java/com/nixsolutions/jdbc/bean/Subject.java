package com.nixsolutions.jdbc.bean;

public class Subject extends AbstractBean {

  private static final long serialVersionUID = 6810729967374225224L;

  private String subjectName;
  private Integer teacherId;
  private Integer semesterId;

  public Subject() {
  }

  public Subject(String subjectName, Integer teacherId, Integer semesterId) {
    super();
    this.subjectName = subjectName;
    this.teacherId = teacherId;
    this.semesterId = semesterId;
  }

  public Subject(Integer id, String subjectName, Integer teacherId, Integer semesterId) {
    this(subjectName, teacherId, semesterId);
    this.id = id;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public Integer getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(Integer teacherId) {
    this.teacherId = teacherId;
  }

  public Integer getSemesterId() {
    return semesterId;
  }

  public void setSemesterId(Integer semesterId) {
    this.semesterId = semesterId;
  }

  @Override
  public String toString() {
    return "Subject [id=" + id + ", subjectName=" + subjectName + ", teacher=" + teacherId + ", semester=" + semesterId
	+ "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((semesterId == null) ? 0 : semesterId.hashCode());
    result = prime * result + ((subjectName == null) ? 0 : subjectName.hashCode());
    result = prime * result + ((teacherId == null) ? 0 : teacherId.hashCode());
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
    if (semesterId == null) {
      if (other.semesterId != null)
	return false;
    } else if (!semesterId.equals(other.semesterId))
      return false;
    if (subjectName == null) {
      if (other.subjectName != null)
	return false;
    } else if (!subjectName.equals(other.subjectName))
      return false;
    if (teacherId == null) {
      if (other.teacherId != null)
	return false;
    } else if (!teacherId.equals(other.teacherId))
      return false;
    return true;
  }
}
