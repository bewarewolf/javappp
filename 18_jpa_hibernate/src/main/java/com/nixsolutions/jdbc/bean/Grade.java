package com.nixsolutions.jdbc.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Grade implements Serializable {

  private static final long serialVersionUID = -4705584854029058600L;
  
  @Id
  @Column(name = "grade_id")
  private Integer id;
  
  @Column(name = "description", length = 100, nullable = false)
  @NotNull
  private String description;
  
  @Column(name = "value", nullable = false, unique = true)
  @NotNull
  private Integer value;
  
  public Grade() {}
  
  public Grade(String description, Integer value) {
    this.description = description;
    this.value = value;
  }
  
  public Grade(Integer id, String description, Integer value) {
    this(description, value);
    this.id = id;
  }
  
  
  public Integer getId() {
    return id;
  }

  
  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public Integer getValue() {
    return value;
  }
  
  public void setValue(Integer value) {
    this.value = value;
  }  
  

  @Override
  public String toString() {
    return "Grade [id=" + id + ", description=" + description + ", value=" + value + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((value == null) ? 0 : value.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (getClass() != obj.getClass())
      return false;
    Grade other = (Grade) obj;
    if (description == null) {
      if (other.description != null)
	return false;
    } else if (!description.equals(other.description))
      return false;
    if (value == null) {
      if (other.value != null)
	return false;
    } else if (!value.equals(other.value))
      return false;
    return true;
  }  
}
