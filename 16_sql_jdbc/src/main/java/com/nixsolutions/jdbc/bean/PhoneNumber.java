package com.nixsolutions.jdbc.bean;

public class PhoneNumber {

  private Integer id;
  private Integer personId;
  private String phoneNumber;
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public Integer getPersonId() {
    return personId;
  }
  
  public void setPersonId(Integer personId) {
    if (personId != null) {
      this.personId = personId;
    } else {
      throw new RuntimeException("Person ID should not be null");
    }
  }
  
  public String getPhoneNumber() {
    return phoneNumber;
  }
  
  public void setPhoneNumber(String phoneNumber) {
    if (phoneNumber != null) {
      this.phoneNumber = phoneNumber;
    } else {
      throw new RuntimeException("Phone number should not be null");
    }
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ID: ");
    sb.append(id);
    sb.append("; Person id: ");
    sb.append(personId );
    sb.append("; Number: ");
    sb.append(phoneNumber);
    return sb.toString();
  }
}
