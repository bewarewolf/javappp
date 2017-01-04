package com.nixsolutions.jdbc.bean;

public class PhoneNumber extends AbstractBean {

  private static final long serialVersionUID = -8047594668701871392L;
  
  private Integer personId;
  private String phoneNumber;
  
  public PhoneNumber() {}
  
  public PhoneNumber(Integer personId, String phoneNumber) {
    super();
    this.personId = personId;
    this.phoneNumber = phoneNumber;
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
