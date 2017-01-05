package com.nixsolutions.jdbc.bean;

import java.time.LocalDate;

public class Person extends AbstractBean {

  private static final long serialVersionUID = -7363295378483616881L;
  
  private String firstName;
  private String middleName;
  private String lastName;
  private LocalDate birthday;
  private LocalDate startDate;
  private Integer personTypeId;
  private Integer personStatusId;
  
  public Person() {}

  public Person(String firstName, String middleName, String lastName, LocalDate birthday, LocalDate startDate,
      Integer personTypeId, Integer personStatusId) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.startDate = startDate;
    this.personTypeId = personTypeId;
    this.personStatusId = personStatusId;
  }
  
  public Person(Integer id, String firstName, String middleName, String lastName, LocalDate birthday, 
      LocalDate startDate, Integer personTypeId, Integer personStatusId) {
    this(firstName, middleName, lastName, birthday, startDate, personTypeId, personStatusId);
    this.id = id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
    result = prime * result + ((personStatusId == null) ? 0 : personStatusId.hashCode());
    result = prime * result + ((personTypeId == null) ? 0 : personTypeId.hashCode());
    result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
    Person other = (Person) obj;
    if (birthday == null) {
      if (other.birthday != null)
	return false;
    } else if (!birthday.equals(other.birthday))
      return false;
    if (firstName == null) {
      if (other.firstName != null)
	return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    if (lastName == null) {
      if (other.lastName != null)
	return false;
    } else if (!lastName.equals(other.lastName))
      return false;
    if (middleName == null) {
      if (other.middleName != null)
	return false;
    } else if (!middleName.equals(other.middleName))
      return false;
    if (personStatusId == null) {
      if (other.personStatusId != null)
	return false;
    } else if (!personStatusId.equals(other.personStatusId))
      return false;
    if (personTypeId == null) {
      if (other.personTypeId != null)
	return false;
    } else if (!personTypeId.equals(other.personTypeId))
      return false;
    if (startDate == null) {
      if (other.startDate != null)
	return false;
    } else if (!startDate.equals(other.startDate))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Person [id=" + id + "firstName=" + firstName + ", middleName=" + middleName 
	+ ", lastName=" + lastName + ", birthday="
	+ birthday + ", startDate=" + startDate + ", personTypeId=" + personTypeId + ", personStatusId="
	+ personStatusId + "]";
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
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
    this.lastName = lastName;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public Integer getPersonTypeId() {
    return personTypeId;
  }

  public void setPersonTypeId(Integer personTypeId) {
    this.personTypeId = personTypeId;
  }

  public Integer getPersonStatusId() {
    return personStatusId;
  }

  public void setPersonStatusId(Integer personStatusId) {
    this.personStatusId = personStatusId;
  }
}
