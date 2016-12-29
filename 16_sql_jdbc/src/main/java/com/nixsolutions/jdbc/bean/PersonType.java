package com.nixsolutions.jdbc.bean;

public class PersonType {

  private Integer id;
  private String description;
  private String value;
  
  public PersonType() {}
  
  public PersonType(String description, String value) {
    this.description = description;
    this.value = value;
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
    if (description != null) {
      this.description = description;
    } else {
      throw new RuntimeException("Description should not be null");
    }
  }
  
  public String getValue() {
    return value;
  }
  
  public void setValue(String value) {
    if (value != null) {
      this.value = value;
    } else {
      throw new RuntimeException("Value should not be null");
    }
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ID: ");
    sb.append(id);
    sb.append("; Description: ");
    sb.append(description);
    sb.append("; Value: ");
    sb.append(value);
    return sb.toString();
  }
}
