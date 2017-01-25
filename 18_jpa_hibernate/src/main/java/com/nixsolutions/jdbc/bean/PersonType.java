package com.nixsolutions.jdbc.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "person_type")
public class PersonType implements Serializable {

  private static final long serialVersionUID = 7128325468862421830L;

  @Id
  @Column(name = "person_type_id")
  private Integer id; 
  @Column(name = "description")
  private String description;
  @Column(name = "value")
  private String value;

  public PersonType() {
  }

  public PersonType(String description, String value) {
    this.description = description;
    this.value = value;
  }

  public PersonType(Integer id, String description, String value) {
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

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "PersonType [id=" + id + ", description=" + description + ", value=" + value + "]";
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
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    PersonType other = (PersonType) obj;
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