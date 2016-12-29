package com.nixsolutions.jdbc.bean;

import java.time.LocalDate;
import java.util.List;

public class Person {

  private Integer id;
  private String firstName;
  private String middleName;
  private String lastName;
  private LocalDate birthday;
  private LocalDate startDate;
  private PersonType personType;
  private PersonStatus personStatus;
  private List<PhoneNumber> phoneNumbers;
  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }
  
  public void setFirstName(String firstName) {
    if (firstName != null) {
      this.firstName = firstName;
    } else {
      throw new RuntimeException("First name should not be null");
    }
  }
  
  public String getMiddleName() {
    return middleName;
  }
  
  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }
  
  public String getLastName() {
    return lastName;
  }
  
  public void setLastName(String lastName) {
    if (lastName != null) {
      this.lastName = lastName;
    } else {
      throw new RuntimeException("Last name should not be null");
    }
  }
  
  public LocalDate getBirthday() {
    return birthday;
  }
  
  public void setBirthday(LocalDate birthday) {
    if (birthday != null) {
      this.birthday = birthday;
    } else {
      throw new RuntimeException("Birthday should not be null");
    }
  }
  
  public LocalDate getStartDate() {
    return startDate;
  }
  
  public void setStartDate(LocalDate startDate) {
    if (startDate != null) {
      this.startDate = startDate;
    } else {
      throw new RuntimeException("Start date should not be null");
    }
  }
  
  public PersonType getPersonType() {
    return personType;
  }
  
  public void setPersonType(PersonType personType) {
    if (personType != null) {
      this.personType = personType;
    } else {
      throw new RuntimeException("Person type should not be null");
    }
  }
  
  public PersonStatus getPersonStatus() {
    return personStatus;
  }
  
  public void setPersonStatus(PersonStatus personStatus) {
    if (personStatus != null) {
      this.personStatus = personStatus;
    } else {
      throw new RuntimeException("Person status should not be null");
    }
  }

  public List<PhoneNumber> getPhoneNumbers() {
    return phoneNumbers;
  }

  public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }
}
