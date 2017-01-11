package com.nixsolutions.jdbc.bean;

public class PhoneNumber extends AbstractBean {

  private static final long serialVersionUID = -8047594668701871392L;

  private Integer personId;
  private String phoneNumber;

  public PhoneNumber() {
  }

  public PhoneNumber(Integer personId, String phoneNumber) {
    this.personId = personId;
    this.phoneNumber = phoneNumber;
  }

  public PhoneNumber(Integer id, Integer personId, String phoneNumber) {
    this(personId, phoneNumber);
    this.id = id;
  }

  public Integer getPersonId() {
    return personId;
  }

  public void setPersonId(Integer personId) {
    this.personId = personId;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public String toString() {
    return "PhoneNumber [id=" + id + ", personId=" + personId + ", phoneNumber=" + phoneNumber + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((personId == null) ? 0 : personId.hashCode());
    result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
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
    PhoneNumber other = (PhoneNumber) obj;
    if (personId == null) {
      if (other.personId != null)
        return false;
    } else if (!personId.equals(other.personId))
      return false;
    if (phoneNumber == null) {
      if (other.phoneNumber != null)
        return false;
    } else if (!phoneNumber.equals(other.phoneNumber))
      return false;
    return true;
  }
}
