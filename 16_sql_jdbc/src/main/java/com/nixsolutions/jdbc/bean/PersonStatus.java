package com.nixsolutions.jdbc.bean;

public class PersonStatus extends AbstractBean {

  private static final long serialVersionUID = -3617840613205325201L;
  
  private String description;
  private String value;
  
  public PersonStatus() {}
  
  public PersonStatus(String description, String value) {
    super();
    this.description = description;
    this.value = value;
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
