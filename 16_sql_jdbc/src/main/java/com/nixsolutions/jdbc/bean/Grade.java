package com.nixsolutions.jdbc.bean;

public class Grade {

  private Integer id;
  private String description;
  private Integer value;
  
  public Grade() {}
  
  public Grade(String description, Integer value) {
    this.description = description;
    this.value = value;
  }
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer gradeId) {
    this.id = gradeId;
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
