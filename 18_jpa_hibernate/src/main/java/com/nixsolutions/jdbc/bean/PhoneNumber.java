package com.nixsolutions.jdbc.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "phone_number")
public class PhoneNumber implements Serializable {

  private static final long serialVersionUID = -8047594668701871392L;

  @Id
  @Column(name = "phone_number_id")
  private Integer id;
  
  @Column(name = "phone_number", length = 20, nullable = false, unique = true)
  private String phoneNumber;

  public PhoneNumber() {
  }

  public PhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public PhoneNumber(Integer id, String phoneNumber) {
    this(phoneNumber);
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public String toString() {
    return "PhoneNumber [id=" + id + ", phoneNumber=" + phoneNumber + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
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
    if (phoneNumber == null) {
      if (other.phoneNumber != null)
        return false;
    } else if (!phoneNumber.equals(other.phoneNumber))
      return false;
    return true;
  }
}
